package cdu_2017.xuye.cov19.autoSerach;
import cdu_2017.xuye.cov19.common.service.SendMailService;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import cdu_2017.xuye.cov19.util.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;
import java.util.*;

@Component
@Slf4j
public class SearchCorssProvince {
    @Autowired
    private UserWayService userWayService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SendMailService sendMailService;
    @Scheduled(cron = "0 0 8 1/1 * ?")
    public void searchCorssProvince() throws JobExecutionException, InterruptedException {
        log.info("----------------跨省检测开启---------------------");
        List<UserWay> userWays = userWayService.list(Wrappers.lambdaQuery(UserWay.class)
        .ge(UserWay::getEndTime, DateUtil.addDateDays(DateUtil.getDayStart(new Date()),-7)));

        userWays.removeIf(userWay -> userWay.getStartProvince().equals(userWay.getEndProvince()));
        List<String> idNumbers = new ArrayList<>();
        if (userWays.size()<=0){
            log.info("--------------结束任务：未检测到跨省行程!-----------------");
            return;
        }
        for (UserWay u:userWays) {
            idNumbers.add(u.getIdNumber());
        }
        List<UserInfo> list = userInfoService.list(Wrappers.lambdaUpdate(UserInfo.class)
                        .in(UserInfo::getIdNumber,idNumbers)
                        .eq(UserInfo::getStatus,0)
                        .le(UserInfo::getSecurityTime,new Date()));

        userInfoService.update(Wrappers.lambdaUpdate(UserInfo.class)
                .in(UserInfo::getIdNumber,idNumbers)
                .eq(UserInfo::getStatus,0)
                .le(UserInfo::getSecurityTime,new Date())
                .set(UserInfo::getStatus,1)
                .set(UserInfo::getDangerStart,DateUtil.getDayStart(new Date()))
                .set(UserInfo::getDangerTime,DateUtil.addDateDays(DateUtil.getDayStart(new Date()),7)));
        String msg = DateUtil.formatDateUnit(new Date())+":\n\t身份证号码:\n";
        List<String> reviceMail = new ArrayList<>();
        for (UserInfo u:list){
            msg +="\t"+u.getIdNumber()+"\t";
            String content = "尊敬的"+u.getName()+"你好:\n\t\t\t系统检测到您最近七天有跨省行为，已将您的风险状态置为中低风险！如未有跨省行程，请联系qq：1642412599申述。若无其他异常情况，系统将在7天后:"+DateUtil.formatDateUnit(DateUtil.addDateDays(new Date(),7))+"为您自动恢复风险程度为低的状态！！";
            sendMailService.sendMail("1783532111@qq.com", Collections.singletonList(u.getPhone()),"疫情登记信息：个人风险等级更改通知",content,"qsbifbfvguhneffc");
            Thread.sleep(5000);
        }
        /**
         * 配置接收邮箱
         */
//        System.out.println("------------发送邮箱"+msg+"---------------");
        if (list.size()>0){
            List<String> mails = new ArrayList<>();
            mails.add("wusifan@sinobyte.cn");
            sendMailService.sendMail("1783532111@qq.com",mails,"跨省健康码变黄名单：",msg,"qsbifbfvguhneffc");
        }
    }
}
