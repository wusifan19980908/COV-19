package cdu_2017.xuye.cov19.register.controller;
import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.dao.UserWayDao;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import cdu_2017.xuye.cov19.util.DateUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @date 2021-02-09 14:32:41
 */
@RestController
@RequestMapping("cov-19/userway")
public class UserWayController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserWayService userWayService;
    @Autowired
    private UserWayDao userWayDao;
    @Autowired
    private RedisRepository redisRepository;
    @RequestMapping("save")
    public R save(@RequestBody UserWay userWay, HttpServletRequest request){
        UserInfo userInfo = userInfoService.getById(userWay.getIdNumber());
        userWay.setStartTime(DateUtil.getDayStart(userWay.getStartTime()));
        userWay.setEndTime(DateUtil.getDayStart(userWay.getEndTime()));
        if (userInfo.getStatus()==0){
            userWay.setFlag(0);
        }else{
            if ((userWay.getStartTime().after(DateUtil.addDateDays(userInfo.getDangerStart(),-7))&&userWay.getStartTime().before(userInfo.getDangerTime()))||(userWay.getEndTime().before(userInfo.getDangerTime())&&userWay.getEndTime().after(DateUtil.addDateDays(userInfo.getDangerStart(),-7)))) {
                switch ((userInfo.getStatus())) {
                    case 1:
                    case 2:
                        userWay.setFlag(1);
                        break;
                    case 3:
                    case 4:
                        userWay.setFlag(2);
                        break;
                    case 5:
                        userWay.setFlag(3);
                        break;
                }
                userWay.setDangerStart(userInfo.getDangerStart());
                userWay.setDangerEnd(userInfo.getDangerTime());
            }
        }
        userWayService.save(userWay);
        return R.ok();
    }
    @RequestMapping("info")
    public UserWay info(Integer id){
            return userWayService.info(id);
    }
    @RequestMapping("listByidNumber")
    public R listByidNumber(String token){
            List<UserWay> list = userWayService.listByidNumber((String) redisRepository.get("number:"+token));
            return R.put(list);
    }
    @RequestMapping("getProvinces")
    public Map<String,Object> getProvinces(){
        Map<String,Object> map = new HashMap<>();
        map.put("provinces",userWayDao.getProvinces());
        return R.put(map);
    }
    @RequestMapping("getCities")
    public Map<String,Object> getCities(Integer province){
        Map<String,Object> map = new HashMap<>();
        map.put("cities",userWayDao.getCities(province));
        return R.put(map);
    }
    @RequestMapping("getAreas")
    public Map<String,Object> getAreas(Integer city){
        Map<String,Object> map = new HashMap<>();
        map.put("areas",userWayDao.getAreas(city));
        return R.put(map);
    }
}
