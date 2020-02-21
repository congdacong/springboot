package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    @Autowired
    AccountService accountService;
    @RequestMapping("/index")
    public List<Account> index(){
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        return accountService.findAll();
    }
}
