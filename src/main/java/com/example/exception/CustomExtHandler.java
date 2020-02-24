package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 丛广博
 */
@RestControllerAdvice
public class CustomExtHandler {
    /**
     * 系统异常
     * @return
     */
    @ExceptionHandler(value= Exception.class)
    Response handleException(Exception e){
//        return Response.error(new CustomerException (CustomerExcepitonType.UNKNOWN_ERROR,e.getMessage()));
        return Response.error(e);
    }

    /**
     * 自定义异常
     * @param ge
     * @return
     */
    @ExceptionHandler(value = CustomerException.class)
    Response girlHandle(CustomerException ge) {
//        if(ge.getCode() == CustomerExcepitonType.USER_INSTER_ERROR.getCode()){
//            return Response.error(new CustomerException (CustomerExcepitonType.USER_INSTER_ERROR,ge.getMessage()));
//        }else{
//            return Response.error(new CustomerException (CustomerExcepitonType.SYSTEM_ERROR,ge.getMessage()));
//        }
        return Response.error(ge);
    }
}
