package cdu_2017.xuye.cov19.common.service;

import java.util.List;

public interface SendMailService {
    public boolean sendMail(String sendAddress, List<String> reviceAddress, String title, String content, String pass);
}
