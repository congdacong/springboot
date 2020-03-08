package com.example.service.impl;

import com.example.dao.SysUserDao;
import com.example.entity.SysUser;
import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:congguangbo
 * @Date:2020/3/8 15:54
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserDao sysUserDao;
    @Override
    public SysUser queryUserByName(String userName) {
        return sysUserDao.queryUserByName(userName);
    }
}
