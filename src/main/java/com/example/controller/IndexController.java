package com.example.controller;

import com.example.annotation.Log;
import com.example.entity.SysMenu;
import com.example.entity.SysUser;
import com.example.enums.EnumsConnection;
import com.example.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:congguangbo
 * @Date:2020/3/15 19:00
 */
@RequestMapping("/boo")
@Controller
public class IndexController {
    @Autowired
    private SysMenuService sysMenuService;
    @RequestMapping("/success")
    @Log(describe = "首页查询", businessType = EnumsConnection.BusinessType.State.SELECT)
    public String success(ModelMap modelmap) {
        SysUser sysUser;
        /**
         * SecurityUtils 工具类
         */
        if(!StringUtils.isEmpty(SecurityUtils.getSubject().getPrincipal())){
            sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            List<SysMenu>  menus = sysMenuService.selectMenusByUser(sysUser);
            modelmap.put("menus", menus);
            modelmap.put("user", sysUser);
        }

        return "common/index";


    }
}
