package com.example.controller;

import com.example.annotation.Log;
import com.example.enums.EnumsConnection;
import com.example.exception.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:40
 */
@Controller
@RequestMapping("/boo")
public class LoginController {
    @RequestMapping("/index")
    public String index() {
        return "common/login";

    }
    @RequestMapping("/login")
    @ResponseBody
    @Log(describe = "用户登录", businessType = EnumsConnection.BusinessType.State.LOGON)
    public Response login(String username, String password, ModelMap modelMap,Boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,true);
        try {
            subject.login(token);
            return Response.success();
        } catch (UnknownAccountException uae) {
            return Response.error("There is no user with username of ");
        } catch (IncorrectCredentialsException ice) {
            return Response.error("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException lae) {
            return Response.error("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        } catch (AuthenticationException ae) {
            return Response.error("unexpected condition?  error?");
        }
    }
        @RequestMapping("/logout")
        public String logout(){
            return "common/logout";
    }

        @RequestMapping("/unauth")
        @ResponseBody
        public String unauth(){
            return "没有权限";
    }
}
