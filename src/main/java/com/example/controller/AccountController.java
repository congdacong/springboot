package com.example.controller;

import com.example.annotation.Log;
import com.example.entity.Account;
import com.example.enums.EnumsConnection;
import com.example.service.AccountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     *
     * @return
     */
    @RequestMapping("/select")
    @RequiresPermissions("system:account:select")
    @Log(describe = "用户管理", businessType = EnumsConnection.BusinessType.State.SELECT)
    public List<Account> index() {
        return accountService.findAll();
    }
    /**
     *
     * @return
     */
    @RequestMapping("/select1")
    @RequiresPermissions("system:account:select1")
    @Log(describe = "用户管理", businessType = EnumsConnection.BusinessType.State.SELECT)
    public List<Account> index1() {
        return accountService.findAll();
    }
}

