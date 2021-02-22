package cdu_2017.xuye.cov19;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Cov19Application {

    public static void main(String[] args) {
        SpringApplication.run(Cov19Application.class, args);
    }
}
