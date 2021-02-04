package cdu_2017.xuye.cov19.common.exception;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String ErrorHandler(HttpServletRequest request,Exception e) throws Exception{
    return "请联系管理员";
    }
}
