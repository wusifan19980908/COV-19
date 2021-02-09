package cdu_2017.xuye.cov19.register.dao;


import cdu_2017.xuye.cov19.register.model.UserWay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
@Mapper
public interface UserWayDao extends BaseMapper<UserWay> {
	UserWay getById(Integer id);
}
