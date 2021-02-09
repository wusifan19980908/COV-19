package cdu_2017.xuye.cov19.register.controller;
import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import cdu_2017.xuye.cov19.util.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @date 2021-02-09 14:32:41
 */
@RestController
@RequestMapping("cov-19/userway")
public class UserWayController {
    @Autowired
    private UserWayService userWayService;
    @Autowired
    private RedisRepository redisRepository;
    @RequestMapping("save")
    public R save(UserWay userWay, HttpServletRequest request){
        String token = request.getHeader("token");
        if (token==null){
            token = request.getParameter("token");
        }
        String idNumber = (String) redisRepository.get("token:"+token);
        redisRepository.setExpire("userway:"+idNumber,userWay,100000);
        return R.ok();
    }
    @RequestMapping("info")
    public UserWay info(Integer id){
            return userWayService.info(id);
    }
}
