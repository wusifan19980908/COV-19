package cdu_2017.xuye.cov19.register.model.area;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("area")
public class Area {
    @TableId
    private Integer id;
    private String name;
    private Integer area_code;
    private Integer city_code;
}
