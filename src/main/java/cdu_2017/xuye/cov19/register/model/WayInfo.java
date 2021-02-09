package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @date 2021-02-09 14:32:41
 */
@Data
@TableName("way_info")
public class WayInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 路线id
	 */
	@TableId
	private Integer wayId;
	/**
	 * 身份证号
	 */
	private String idNumber;
	/**
	 * 出行方式:0为私家车，1为公共交通，2为大巴或火车，3为航班
	 */
	private Integer wayType;
	/**
	 * 私家车车牌，公共交通路线，大巴火车班次，航班号
	 */
	private String code;

}
