package com.example.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * congguangbo
 */
@RestControllerAdvice
public class CustomExtHandler {
    /**
     * 系统异常
     * @return
     */
    @ExceptionHandler(value= Exception.class)
    Response handleException(Exception e){
        return Response.error(e);
    }

    /**
     * 自定义异常
     * @param ge
     * @return
     */
    @ExceptionHandler(value = CustomerException.class)
    Response girlHandle(CustomerException ge) {
        return Response.error(ge);
    }
    /**
     * shiro 异常
     * @param ge
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    Response unknownAccount(AuthenticationException ge) {
        return Response.error(ge);
    }
}
