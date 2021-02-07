package cdu_2017.xuye.cov19.common.config;
import cdu_2017.xuye.cov19.common.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class MyWebAppConfig implements WebMvcConfigurer {
    @Autowired
    private RequestFilter requestFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/cov-19/user/login")
                .excludePathPatterns("/cov-19/user/register");
    }
}
