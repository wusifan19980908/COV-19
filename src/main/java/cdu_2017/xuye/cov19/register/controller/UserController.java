package cdu_2017.xuye.cov19.register.controller;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.User;
import cdu_2017.xuye.cov19.register.service.UserService;
import cdu_2017.xuye.cov19.util.PasswordUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cov-19/user")
public class UserController {
    @Autowired
    private UserService userService;
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
                redisRepository.setExpire("token:" +user.getIdNumber(), token, 10);
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
    public R register(User user){
        User u = userService.getByNumber(user.getIdNumber());
        if (u==null){
            user.setSalt(PasswordUtil.getSalt());
            user.setPassword(PasswordUtil.sha256(user.getSalt(),user.getPassword()));
            userService.save(user);
            return R.ok();
        }
        return R.put("该身份证号已注册，请登录！");
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
