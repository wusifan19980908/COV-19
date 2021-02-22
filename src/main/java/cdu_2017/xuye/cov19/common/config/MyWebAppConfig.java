package cdu_2017.xuye.cov19.common.config;
import cdu_2017.xuye.cov19.common.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class MyWebAppConfig implements WebMvcConfigurer {
    @Autowired
    private RequestFilter requestFilter;

    /**
     * 添加全局拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/cov-19/user/login")
                .excludePathPatterns("/cov-19/user/register")
                .excludePathPatterns("/cov-19/userway/getProvinces")
                .excludePathPatterns("/cov-19/userway/getCities")
                .excludePathPatterns("/cov-19/userway/getAreas")
                .excludePathPatterns("/static/**");
    }

    /**
     * 开启跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}
