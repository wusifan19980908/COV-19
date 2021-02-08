package cdu_2017.xuye.cov19.register.model.area;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("province")
public class Province {
    @TableId
    private Integer id;
    private String name;
    private Integer provinceCode;
}
