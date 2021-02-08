package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    /**
     * 城市
     */
    @TableField(exist = false)
    private Integer cityCode;
    /**
     * 区域
     */
    @TableField(exist = false)
    private Integer areaCode;
}
