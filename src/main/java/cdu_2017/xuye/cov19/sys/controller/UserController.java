package cdu_2017.xuye.cov19.sys.controller;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.sys.model.User;
import cdu_2017.xuye.cov19.sys.service.UserService;
import cdu_2017.xuye.cov19.util.DateUtil;
import cdu_2017.xuye.cov19.util.PasswordUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("cov-19/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private RedisRepository redisRepository;
    /**
     * 登陆方法
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("login")
    public R login(User user) throws Exception {
        User u =  userService.getByNumber(user.getIdNumber());
        if (u==null){
            return R.error("身份证记录不存在");
        }
            if (u.getPassword().equals(PasswordUtil.sha256(u.getSalt(), user.getPassword()))) {
                String token = PasswordUtil.getToken(u.getPassword());
                redisRepository.setExpire("number:"+token,u.getIdNumber(),1000);
                redisRepository.setExpire("token:" +token, token, 1000);
//                redisRepository.setExpire("password:"+token,u.getSalt(),1000);
                return R.put(token);
            }
        return  R.error("密码错误");
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public R register(@RequestParam Map<String,Object> map){
        User user = new User();
        UserInfo userInfo = new UserInfo();
        UserArea userArea = new UserArea();
        User u = userService.getByNumber((String) map.get("idNumber"));
        if (u==null){
            //设置用户
            user.setIdNumber((String) map.get("idNumber"));
            user.setSalt(PasswordUtil.getSalt());
            user.setPassword(PasswordUtil.sha256(user.getSalt(), (String) map.get("password")));
            //用户信息
            userInfo.setPhone((String) map.get("phone"));
            userInfo.setSex((String) map.get("sex"));
            userInfo.setStatus(Integer.parseInt((String) map.get("status")));
            userInfo.setName((String) map.get("name"));
            userInfo.setIdNumber((String) map.get("idNumber"));
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
            //用户住址
            userArea.setIdNumber((String) map.get("idNumber"));
            userArea.setArea(Integer.parseInt((String) map.get("areaCode")));
            userArea.setCity(Integer.parseInt((String) map.get("cityCode")));
            userArea.setProvince(Integer.parseInt((String) map.get("provinceCode")));
            userArea.setCommunity((String) map.get("community"));
            userService.save(user);
            userInfoService.save(userInfo);
            userAreaService.save(userArea);
            return R.ok();
        }
        return R.error("该身份证号已注册，若非本人注册，请联系管理员！");
    }
    /**
     * 修改账号信息
     */
    @RequestMapping("update")
    public R update(User user){
        User u = userService.getById(user.getIdNumber());
        user.setPassword(PasswordUtil.sha256(u.getSalt(),user.getPassword()));
        userService.updateById(user);
        return R.ok();
    }
}
