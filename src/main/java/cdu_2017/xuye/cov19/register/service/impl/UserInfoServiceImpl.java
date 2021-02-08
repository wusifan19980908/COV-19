package cdu_2017.xuye.cov19.register.service.impl;
import cdu_2017.xuye.cov19.register.dao.UserAreaDao;
import cdu_2017.xuye.cov19.register.dao.UserInfoDao;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao,UserInfo> implements UserInfoService {
    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserAreaDao userAreaDao;
    /**
     * 保存用户基本信息
     */
    @Override
    public boolean saveUserInfo(UserInfo userInfo) {
        UserArea userArea = new UserArea();
        //存储用户地区信息
        userArea.setIdNumber(userInfo.getIdNumber());
        userArea.setProvince(userInfo.getProvinceCode());
        userArea.setCity(userInfo.getCityCode());
        userArea.setArea(userInfo.getAreaCode());
        userAreaService.save(userArea);
        this.save(userInfo);
        return true;
    }

    /**
     * 查看个人信息
     * @param idNumber
     * @return
     */
    @Override
    public UserInfo info(String idNumber) {
        //获取个人信息
        UserInfo userInfo = this.getById(idNumber);
        //获取所在地区信息
        UserArea userArea = userAreaService.getById(idNumber);
        userInfo.setProvinceCode(userArea.getProvince());
        userInfo.setCityCode(userArea.getCity());
        userInfo.setAreaCode(userArea.getArea());
        return userInfo;
    }

    /**
     * 修改个人信息
     * @param userInfo
     * @return
     */
    @Override
    public boolean update(UserInfo userInfo) {
        UserArea userArea = new UserArea();
        userArea.setIdNumber(userInfo.getIdNumber());
        userArea.setProvince(userInfo.getProvinceCode());
        userArea.setCity(userInfo.getCityCode());
        userArea.setArea(userInfo.getAreaCode());
        this.updateById(userInfo);
        userAreaService.updateById(userArea);
        return true;
    }
}
