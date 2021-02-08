package cdu_2017.xuye.cov19.register.model.area;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city")
public class City {
    @TableId
    private Integer id;
    private String name;
    private Integer city_code;
    private Integer province_code;
}
