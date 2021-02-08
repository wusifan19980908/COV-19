package cdu_2017.xuye.cov19.register.dao;

import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.area.Area;
import cdu_2017.xuye.cov19.register.model.area.City;
import cdu_2017.xuye.cov19.register.model.area.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserAreaDao extends BaseMapper<UserArea> {
    UserArea getUserAreaByIdNumber(String idNumber);
}
