package cdu_2017.xuye.cov19;
import cdu_2017.xuye.cov19.autoSerach.AutoResumeStatus;
import org.junit.jupiter.api.Test;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Cov19ApplicationTests {
    @Autowired
    private AutoResumeStatus autoResumeStatus;
    @Test
    void contextLoads() throws JobExecutionException, InterruptedException {
        autoResumeStatus.autoResumeStatus();
    }

}
