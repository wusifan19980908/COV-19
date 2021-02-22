package cdu_2017.xuye.cov19.common.filter;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.util.RedisRepository;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class RequestFilter implements HandlerInterceptor {
    @Autowired
    private RedisRepository redisRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin","*");//允许所有域名访问
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        //跨域 Header
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE,token");
        //浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
        // 配置options的请求返回
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            // hresp.setContentLength(0);
            response.getWriter().write("OPTIONS returns OK");
            return true;
        }
        String token = request.getHeader("token");
        if (token==null){
            token = request.getParameter("token");
        }
        String idNumber = request.getParameter("idNumber");
        if (redisRepository.get("token:"+token)!=null){
            redisRepository.setExpire("token:"+token,token,1000);
            redisRepository.setExpire("number:"+token,redisRepository.get("number:"+token),1000);
            return true;
        }
        R r = new R();
        r.put("code",500);
        r.put("msg","token过期");
        String json = JSONUtil.toJsonStr(r);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
