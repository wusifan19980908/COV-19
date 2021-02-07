package cdu_2017.xuye.cov19.register.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    //用户名
    @TableId
    private String idNumber;
    //密码
    private String password;
    //加密字段
    private String salt;
}
