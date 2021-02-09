package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	@TableId
	private Integer id;
	/**
	 * 身份证号
	 */
	private String idNumber;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 出发区域代码
	 */
	private Integer startArea;
	/**
	 * 目的地区域代码
	 */
	private Integer endArea;

	/**
	 * 行程交通工具
	 */
	@TableField(exist = false)
	private List<WayInfo> wayInfos;

}
