package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 22:12
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("/login")
    public String login(){
        return "/common/login";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "/common/logout";
    }
}
