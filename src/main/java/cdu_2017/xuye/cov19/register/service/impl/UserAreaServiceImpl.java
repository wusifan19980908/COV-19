package cdu_2017.xuye.cov19.register.service.impl;

import cdu_2017.xuye.cov19.register.dao.UserAreaDao;
import cdu_2017.xuye.cov19.register.dao.UserInfoDao;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userAreaService")
public class UserAreaServiceImpl extends ServiceImpl<UserAreaDao, UserArea> implements UserAreaService {
}
