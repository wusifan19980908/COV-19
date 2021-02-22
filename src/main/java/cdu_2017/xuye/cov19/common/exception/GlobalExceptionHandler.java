package cdu_2017.xuye.cov19.common.exception;

import cdu_2017.xuye.cov19.common.model.R;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R ErrorHandler(HttpServletResponse response, HttpServletRequest request, Exception e) throws Exception{
        e.printStackTrace();
        if (e.getMessage()==null){
            return R.error("请联系管理员");
        }
        return R.error(e.getMessage());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        return R.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e){
        return R.error("数据库中已存在该记录");
    }
}
