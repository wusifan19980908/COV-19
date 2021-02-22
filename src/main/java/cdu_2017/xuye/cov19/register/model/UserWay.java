package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
@Data
@TableName("user_way")
public class UserWay implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 路线id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 身份证号
	 */
	private String idNumber;
	/**
	 * 开始时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 出发省代码
	 */
	private Integer startProvince;
	@TableField(exist = false)
	private String startProvinceName;
	/**
	 * 出发城市
	 */
	private Integer startCity;
	@TableField(exist = false)
	private String startCityName;
	/**
	 * 出发区域代码
	 */
	private Integer startArea;
	@TableField(exist = false)
	private String startAreaName;
	/**
	 * 目的省代码
	 */
	private Integer endProvince;
	@TableField(exist = false)
	private String endProvinceName;
	/**
	 * 目的城市代码
	 */
	private Integer endCity;
	@TableField(exist = false)
	private String endCityName;
	/**
	 * 目的地区域代码
	 */
	private Integer endArea;
	@TableField(exist = false)
	private String endAreaName;
	/**
	 * 行程风险等级
	 */
	private Integer flag;
	/**
	 * 危险触发时间
	 */
	private Date dangerStart;
	/**
	 * 危险接触时间
	 */
	private Date dangerEnd;
	/**
	 * 行程交通工具
	 */
	@TableField(exist = false)
	private List<WayInfo> wayInfos;

}
