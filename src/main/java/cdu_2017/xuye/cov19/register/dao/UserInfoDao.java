package cdu_2017.xuye.cov19.register.dao;

import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.model.area.Area;
import cdu_2017.xuye.cov19.register.model.area.City;
import cdu_2017.xuye.cov19.register.model.area.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {
    /**
     * 获取userinfo
     * @param idNumber
     * @return
     */
    UserInfo getUserInfoByIdNumber(String idNumber);

    /**
     * 通过省份代码获取名字
     * @param provinceCode
     * @return
     */
    String getProvinceByCode(Integer provinceCode);

    /**
     * 通过城市代码获取名字
     * @param cityCode
     * @return
     */
    String getCityByCode(Integer cityCode);

    /**
     * 通过区县代码获取名字
     * @param areaCode
     * @return
     */
    String getAreaByCode(Integer areaCode);
    /**
     * 通过省份代码获取城市列表
     */
    List<City> listCity(Integer provinceCode);
    /**
     * 通过城市代码获取区县列表
     */
    List<Area> listArea(Integer cityCode);
    /**
     * 获取省份列表
     */
    List<Province> listProvince();
}
