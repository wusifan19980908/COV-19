package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class UserInfo {
    //身份证号码
    @TableId
    private String idNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 风险级别
     */
    private Integer status;
    /**
     * 省份
     */
    @TableField(exist = false)
    private Integer provinceCode;
    @TableField(exist = false)
    private String provinceName;
    /**
     * 城市
     */
    @TableField(exist = false)
    private Integer cityCode;
    @TableField(exist = false)
    private String cityName;
    /**
     * 区域
     */
    @TableField(exist = false)
    private Integer areaCode;
    @TableField(exist = false)
    private String areaName;
    /**
     * 社区
     */
    @TableField(exist = false)
    private String community;
    /**
     * 危险触发时间
     */
    private Date dangerStart;
    /**
     * 危险接触时间
     */
    private Date dangerTime;
    /**
     * 安全截至时间
     */
    private Date securityTime;
}
