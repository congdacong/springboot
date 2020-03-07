package com.example.controller;

import com.example.annotation.Log;
import com.example.entity.Account;
import com.example.enums.EnumsConnection;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     *
     * @return
     */
    @RequestMapping("/select")
    @Log(describe = "用户管理", businessType = EnumsConnection.BusinessType.State.SELECT)
    public List<Account> index() {
        return accountService.findAll();
    }
}
