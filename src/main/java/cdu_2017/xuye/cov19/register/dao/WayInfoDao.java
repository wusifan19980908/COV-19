package cdu_2017.xuye.cov19.register.dao;

import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.WayInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
@Mapper
public interface WayInfoDao extends BaseMapper<WayInfo> {
    List<String> checkWayInfo(WayInfo wayInfo);
}
