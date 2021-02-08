package cdu_2017.xuye.cov19.register.controller;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("cov-19/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("save")
    public R save(UserInfo userInfo){
        userInfoService.saveUserInfo(userInfo);
        return R.ok();
    }
    @RequestMapping("info")
    public UserInfo info(String idNumber){
        return userInfoService.info(idNumber);
    }
    @RequestMapping("update")
    public R update(UserInfo userInfo){
        userInfoService.update(userInfo);
        return R.ok();
    }
}
