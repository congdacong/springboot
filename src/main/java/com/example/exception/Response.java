package com.example.exception;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import java.util.HashMap;

/**
 * congguangbo 2019.12.02
 * @param
 */
public class Response {

    private boolean isok;
    private String message;
    private int code;
    private Object data;

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Response() {

    }
    public static Response error(CustomerException e){
        Response response = new Response();
        response.setIsok(false);
        response.setCode(e.getCode());
        if(e.getCode() == CustomerExcepitonType.SYSTEM_ERROR.getCode()){
            response.setMessage(e.getMessage()+"系统错误，请联系管理员！");
        }else if(e.getCode() == CustomerExcepitonType.USER_INSTER_ERROR.getCode()){
            response.setMessage(e.getMessage()+"用户输入错误，请重新输入");
        }else{
            response.setMessage(e.getMessage()+"未知错误！");
        }
        return response;
    }

    /**
     * 系统异常
     * @param e
     * @return
     */
    public static Response error(Exception e){
        Response response = new Response();
        response.setIsok(false);
        response.setMessage(CustomerExcepitonType.UNKNOWN_ERROR.getDescription());
        response.setCode(CustomerExcepitonType.UNKNOWN_ERROR.getCode());
        return response;
    }

    /**
     * shiro 异常
     * @param e
     * @return
     */
    public static Response error(AuthenticationException e){
        Response response = new Response();
        response.setIsok(false);
        response.setMessage(e.getMessage());
        response.setCode(CustomerExcepitonType.UserNotExist.getCode());
        return response;
    }
    public Response success(){
        Response response = new Response();
        response.setIsok(true);
        response.setCode(200);
        response.setMessage("success");
        return response;
    }
    public Response success(Object data){
        Response response = new Response();
        response.setIsok(true);
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
}
