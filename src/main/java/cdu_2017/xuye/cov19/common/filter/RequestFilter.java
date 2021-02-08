package cdu_2017.xuye.cov19.common.filter;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.util.RedisRepository;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestFilter implements HandlerInterceptor {
    @Autowired
    private RedisRepository redisRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token==null){
            token = request.getParameter("token");
        }
        String idNumber = request.getParameter("idNumber");
        if (redisRepository.get("token:"+token)!=null){
            redisRepository.setExpire("token:"+token,token,1000);
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
