package cdu_2017.xuye.cov19.register.service;

import cdu_2017.xuye.cov19.register.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserService extends IService<User>{
   List<User> list(Map<String,Object> map);
   User selectById(Integer id);
   User getByNumber(String number);
}
