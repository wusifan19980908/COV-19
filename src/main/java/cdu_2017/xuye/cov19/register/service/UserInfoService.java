package cdu_2017.xuye.cov19.register.service;

import cdu_2017.xuye.cov19.register.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface UserInfoService extends IService<UserInfo> {
    public boolean saveOrUpdateUserInfo(UserInfo userInfo);
    public UserInfo info(String  idNumber);
    public boolean update(UserInfo userInfo);
}
