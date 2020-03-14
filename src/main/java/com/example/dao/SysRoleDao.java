package com.example.dao;


import com.example.entity.Account;
import com.example.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysRoleDao {
    public List<SysRole> selectRolesByUserId(Long userId);
}
