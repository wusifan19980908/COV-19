package cdu_2017.xuye.cov19.register.service.impl;

import cdu_2017.xuye.cov19.register.dao.UserDao;
import cdu_2017.xuye.cov19.register.model.User;
import cdu_2017.xuye.cov19.register.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> list(Map<String, Object> map) {
        return null;
    }

    @Override
    public User selectById(Integer id) {
        return null;
    }

    @Override
    public User getByNumber(String number) {
        return userDao.getByNumber(number);
    }

}
