package cdu_2017.xuye.cov19.autoSerach;

import cdu_2017.xuye.cov19.common.service.SendMailService;
import cdu_2017.xuye.cov19.register.dao.WayInfoDao;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.model.WayInfo;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import cdu_2017.xuye.cov19.register.service.WayInfoService;
import cdu_2017.xuye.cov19.util.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class AutoCheckWayHigh {
    @Autowired
    private UserWayService userWayService;
    @Autowired
    private WayInfoService wayInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WayInfoDao wayInfoDao;
    @Autowired
    private SendMailService sendMailService;
//    @Scheduled(cron = "0 0 6 1/1 * ?")
    public void autoCheckWayHigh() throws InterruptedException {
        Set<String> hightNumberTree = new HashSet<>();
        Set<String> idNumberTree = new HashSet<>();
        //获取风险行程
        List<UserWay> list = userWayService.list(Wrappers.lambdaQuery(UserWay.class)
        .in(UserWay::getFlag,3));
        for (UserWay u:list){
            hightNumberTree.add(u.getIdNumber());
        }
        List<WayInfo> wayInfos = new ArrayList<>();
        List<UserWay> userWays = new ArrayList<>();
        for (UserWay u:list){
            //获取风险交通工具
            wayInfos.addAll(wayInfoService.list(Wrappers.lambdaQuery(WayInfo.class)
                    .eq(WayInfo::getWayId,u.getId())));
            userWays.addAll(userWayService.list(Wrappers.lambdaQuery(UserWay.class)
                    .between(UserWay::getStartTime, DateUtil.addDateDays(u.getStartTime(),-7),u.getEndTime())
                    .between(UserWay::getEndTime,DateUtil.addDateDays(u.getStartTime(),-7),u.getEndTime())
                    .eq(UserWay::getStartProvince,u.getStartProvince()).or().eq(UserWay::getEndProvince,u.getEndProvince())));
        }
        Iterator<UserWay> iterator = userWays.iterator();
        while (iterator.hasNext()){
            UserWay userWay = iterator.next();
            for (String s:hightNumberTree){
                if (s.equals(userWay.getIdNumber())){
                    iterator.remove();
                }
            }
        }
        //获取与风险行程相同行程
        List<WayInfo> infos = new ArrayList<>();
        for (UserWay u:userWays){
            infos.addAll(wayInfoService.list(Wrappers.lambdaQuery(WayInfo.class)
                    .eq(WayInfo::getWayId,u.getId())));
        }
        for (WayInfo w:wayInfos){
            for (WayInfo i:infos){
                if (w.getWayType().equals(i.getWayType())&&w.getCode().equals(i.getCode())){
                    idNumberTree.add(i.getIdNumber());
                }
            }
        }
        userInfoService.update(Wrappers.lambdaUpdate(UserInfo.class)
                .in(UserInfo::getIdNumber,idNumberTree)
                .in(UserInfo::getStatus,0,1,2,3)
                .set(UserInfo::getStatus,4)
                .set(UserInfo::getDangerStart,DateUtil.getDayStart(new Date()))
                .set(UserInfo::getDangerTime,DateUtil.addDateDays(DateUtil.getDayStart(new Date()),14)));
        List<UserInfo> high= userInfoService.list(Wrappers.lambdaUpdate(UserInfo.class)
                .in(UserInfo::getIdNumber,idNumberTree));
        for (UserInfo s:high){
            String msg = "尊敬的："+s.getName()+"\n\t\t系统检测到您最近7天与确诊人员有过同程记录。系统已将您的风险级别置为中高级别。申诉请联系qq：1642412599。若无其他风险行程，将在14日后自动恢复";
            sendMailService.sendMail("1783532111@qq.com", Collections.singletonList(s.getPhone()),"近七日与确诊人员同行名单：",msg,"qsbifbfvguhneffc");
            Thread.sleep(500);
        }
    }
}
