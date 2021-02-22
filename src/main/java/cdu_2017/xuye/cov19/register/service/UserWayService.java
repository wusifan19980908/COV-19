package cdu_2017.xuye.cov19.register.service;


import cdu_2017.xuye.cov19.register.model.UserWay;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
public interface UserWayService extends IService<UserWay> {
    public UserWay info(Integer id);
    public List<UserWay> listByidNumber(String idNumber);
}

