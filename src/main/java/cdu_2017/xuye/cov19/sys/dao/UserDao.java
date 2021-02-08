package cdu_2017.xuye.cov19.sys.dao;

import cdu_2017.xuye.cov19.sys.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
    User getByNumber(String idNumber);
}
