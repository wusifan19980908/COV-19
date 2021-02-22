package cdu_2017.xuye.cov19.common.service.impl;

import cdu_2017.xuye.cov19.common.model.MailFactory;
import cdu_2017.xuye.cov19.common.service.SendMailService;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Override
    public boolean sendMail(String sendAddress, List<String> reviceAddress, String title,String content, String pass) {
        MailAccount account = new MailAccount();
        account.setFrom(sendAddress);
        account.setPass(pass);
        try{
            if (sendAddress.contains("@qq")){
                account = initQQmail(account);
            }else{
                throw new Exception("此邮箱类型未配置，需在sendMail和MailFactory中添加相应设置");
            }
            MailUtil.send(account,reviceAddress,title,content,false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    //初始化qq邮箱发送配置
    private MailAccount initQQmail(MailAccount account){
        //qq必须开启ssl
        account.setSslEnable(true);
        account.setHost(MailFactory.QQHost);
        account.setPort(MailFactory.QQport);
        return account;
    }
}
