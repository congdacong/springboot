package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:40
 */
@RestController
@RequestMapping("/boo")
public class LoginController {
    @RequestMapping({"/","/index"})
    public String index(){
        int i = 5/0;
        return "common/login";

    }
    @RequestMapping("/login")
    public String login(String userName,String password){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            return "common/index";
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
