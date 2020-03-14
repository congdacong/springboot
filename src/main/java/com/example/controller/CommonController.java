package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 22:12
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("/login")
    public String login(){
        return "common/login";
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
