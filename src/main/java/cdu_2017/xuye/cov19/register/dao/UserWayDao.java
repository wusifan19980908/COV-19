package cdu_2017.xuye.cov19.register.dao;


import cdu_2017.xuye.cov19.register.model.UserWay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
@Mapper
public interface UserWayDao extends BaseMapper<UserWay> {
	UserWay getById(Integer id);
	List<UserWay> listByIdNumberList(String idNumber);
	List<Map<String,Object>> getProvinces();
	List<Map<String,Object>> getCities(Integer provinceCode);
	List<Map<String,Object>> getAreas(Integer cityCode);
}
