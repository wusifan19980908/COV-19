package cdu_2017.xuye.cov19.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.awt.*;

public class PasswordUtil {
    /**
     *
     * @return 生成密码需要的盐
     */
    public static String getSalt(){
        return RandomStringUtils.randomAlphabetic(64);
    }

    /**
     *
     * @param salt
     * @param password
     * @return sha256
     */
    public static String sha256(String salt,String password){
        return new Sha256Hash(password,salt).toString();
    }
    public static String getToken(String password){
        String token = sha256(System.currentTimeMillis()+" ",password);
        return token;
    }

}
