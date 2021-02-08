package cdu_2017.xuye.cov19.QCgenerated.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_area")
public class Qc {
    @TableId
    private String idNumber;
    /**
     * 二维码信息
     */
    private String qcCode;
    /**
     * 风险级别
     */
    private Integer status;
}
