package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_area")
public class UserArea {
    @TableId
    private String idNumber;
    /**
     * 省份
     */
    private Integer province;
    /**
     * 城市
     */
    private Integer city;
    /**
     * 地区
     */
    private Integer area;
    /**
     * 社区
     */
    private String community;
}
