package cdu_2017.xuye.cov19.QCgenerated.controller;
import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.UserInfo;
import cdu_2017.xuye.cov19.register.service.UserInfoService;
import cdu_2017.xuye.cov19.sys.model.User;
import cdu_2017.xuye.cov19.sys.service.UserService;
import cdu_2017.xuye.cov19.util.QRCodeUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("cov-19/qcGenerated")
public class QcController {
    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping   ("qrCode")
    public R getQRCode(@RequestParam Map<String,Object> map, HttpServletResponse response) throws IOException {
       String token = (String) map.get("token");
       String idNumber = (String) redisRepository.get("number:"+token);
       UserInfo user = userInfoService.getById(idNumber);
       return R.put(user.getStatus());
    }
}
