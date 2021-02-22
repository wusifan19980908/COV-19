package cdu_2017.xuye.cov19.register.service.impl;

import cdu_2017.xuye.cov19.register.dao.UserInfoDao;
import cdu_2017.xuye.cov19.register.dao.UserWayDao;
import cdu_2017.xuye.cov19.register.model.UserWay;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.register.service.UserWayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userWayService")
public class UserWayServiceImpl extends ServiceImpl<UserWayDao, UserWay> implements UserWayService {
    @Autowired
    private UserWayDao userWayDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserWay info(Integer id) {
        return userWayDao.getById(id);
    }

    @Override
    public List<UserWay> listByidNumber(String idNumber) {
        List<UserWay> list = userWayDao.listByIdNumberList(idNumber);
        for (int i=0;i<list.size();i++){
            UserWay userWay = list.get(i);
            userWay.setStartProvinceName(userInfoDao.getProvinceByCode(userWay.getStartProvince()));
            userWay.setStartCityName(userInfoDao.getCityByCode(userWay.getStartCity()));
            userWay.setStartAreaName(userInfoDao.getAreaByCode(userWay.getStartArea()));
            userWay.setEndProvinceName(userInfoDao.getProvinceByCode(userWay.getEndProvince()));
            userWay.setEndCityName(userInfoDao.getCityByCode(userWay.getEndCity()));
            userWay.setEndAreaName(userInfoDao.getAreaByCode(userWay.getEndArea()));
            list.set(i,userWay);
        }
        return list;
    }
}