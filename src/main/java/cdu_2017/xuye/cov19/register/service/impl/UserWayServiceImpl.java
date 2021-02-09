package cdu_2017.xuye.cov19.register.service.impl;

import cdu_2017.xuye.cov19.register.dao.UserWayDao;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;


@Service("userWayService")
public class UserWayServiceImpl extends ServiceImpl<UserWayDao, UserWay> implements UserWayService {
    @Autowired
    private UserWayDao userWayDao;

    @Override
    public UserWay info(Integer id) {
        return userWayDao.getById(id);
    }
}