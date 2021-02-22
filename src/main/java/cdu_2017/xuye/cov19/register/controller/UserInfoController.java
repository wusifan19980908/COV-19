package cdu_2017.xuye.cov19.register.controller;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.model.WayInfo;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import cdu_2017.xuye.cov19.register.service.WayInfoService;
import cdu_2017.xuye.cov19.util.DateUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController

@RequestMapping("cov-19/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserWayService userWayService;
    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    RedisRepository redisRepository;

    @RequestMapping("save")
    public R saveOrUpdate(@RequestParam Map<String,Object> map){
        UserInfo userInfo = new UserInfo();
        userInfo.setIdNumber((String) map.get("idNumber"));
        userInfo.setName((String) map.get("name"));
        userInfo.setSex((String) map.get("sex"));
        userInfo.setPhone((String) map.get("phone"));
        userInfo.setStatus(Integer.parseInt((String)map.get("status")));
        if (userInfo.getStatus()!=0){
            switch (userInfo.getStatus()){
                case 1:
                case 2:userInfo.setDangerStart(DateUtil.getDayStart(new Date()));
                    userInfo.setDangerTime(DateUtil.addDateDays(userInfo.getDangerStart(),7));
                    break;
                case 3:
                case 4:userInfo.setDangerStart(DateUtil.getDayStart(new Date()));
                    userInfo.setDangerTime(DateUtil.addDateDays(userInfo.getDangerStart(),14));
                    break;
                case 5:userInfo.setDangerStart(DateUtil.getDayStart(new Date()));
                    userInfo.setDangerTime(DateUtil.addDateYears(userInfo.getDangerStart(),1));
            }
        }
        userWayService.update(Wrappers.lambdaUpdate(UserWay.class)
                .eq(UserWay::getIdNumber,userInfo.getIdNumber())
                .ge(UserWay::getEndTime,DateUtil.addDateDays(userInfo.getDangerStart(),-14))
                .set(UserWay::getDangerStart,userInfo.getDangerStart())
                .set(UserWay::getDangerEnd,userInfo.getDangerTime())
                .set(UserWay::getFlag,3));
        UserArea userArea = new UserArea();
        userArea.setIdNumber(userInfo.getIdNumber());
        userArea.setArea(Integer.parseInt((String) map.get("areaCode")));
        userArea.setCommunity((String) map.get("community"));
        userArea.setProvince(Integer.parseInt((String) map.get("provinceCode")));
        userArea.setCity(Integer.parseInt((String) map.get("cityCode")));
        userInfoService.saveOrUpdate(userInfo);
        userAreaService.saveOrUpdate(userArea);
        return R.ok();
    }
    @RequestMapping("info")
    public R info(HttpServletRequest request){
        String token = request.getHeader("token");
        String idNumber = (String) redisRepository.get("number:"+token);
        return R.put(userInfoService.info(idNumber));
    }
    @RequestMapping("update")
    public R update(UserInfo userInfo){
        userInfoService.update(userInfo);
        return R.ok();
    }
}
