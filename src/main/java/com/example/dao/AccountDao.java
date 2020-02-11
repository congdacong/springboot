package com.example.dao;


import com.example.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountDao {
    public List<Account> findAll();
}
