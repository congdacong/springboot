package com.example.service.impl;

import com.example.dao.AccountDao;
import com.example.entity.Account;
import com.example.exception.CustomerExcepitonType;
import com.example.exception.CustomerException;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public List<Account> findAll() {
//        throw new CustomerException(CustomerExcepitonType.USER_INSTER_ERROR,CustomerExcepitonType.SYSTEM_ERROR.getDescription());
//        int i = 5/0;
        return accountDao.findAll();
    }
}
