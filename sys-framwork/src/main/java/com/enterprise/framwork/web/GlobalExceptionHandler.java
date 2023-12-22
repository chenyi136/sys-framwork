package com.enterprise.framwork.web;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.common.enums.HttpCodeMsg;
import com.enterprise.common.exception.AuthException;
import com.enterprise.common.exception.ServiceException;
import com.enterprise.common.model.R;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 401错误 请求未授权或授权已失效
     */
    @ExceptionHandler(AuthException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthException e) {
        log.error(e.getMessage(), e);
        log.error("stacktrace ={}", JSONObject.toJSONString(e.getStackTrace()));
        return R.fail(HttpCodeMsg.UN_AUTHORIZED.getCode(), e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return R.fail("不支持[" + e.getMethod() + "[请求");
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public R handleError(ServiceException e) {
        log.error("业务异常", e);
        return R.fail(e.getCodeMessage().getCode(), e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public R validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.fail(message);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R handleRuntimeException(RuntimeException e) {
        log.error("运行时异常:", e);
        return R.fail("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail("服务器错误，请联系管理员");
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.fail(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object validExceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        String message = "无法读取提交内容";
        return R.fail(message);
    }
}
