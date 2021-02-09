package cdu_2017.xuye.cov19.QCgenerated.controller;
import cdu_2017.xuye.cov19.util.QRCodeUtil;
import cdu_2017.xuye.cov19.util.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("cov-19/qcGenerated")
public class QcController {
    @Autowired
    private RedisRepository redisRepository;
    @PostMapping  ("qrCode")
    public void getQRCode(@RequestParam Map<String,Object> map, HttpServletResponse response) {

        System.out.println("codeContent=" + map.toString());
        try {
            /*
             * 调用工具类生成二维码并输出到输出流中
             */
            map.put("color","0");
            QRCodeUtil.createCodeToOutputStream(map, response.getOutputStream());
            log.info("成功生成二维码!");
        } catch (IOException e) {
            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
    }
}
