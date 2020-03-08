package com.example.dao;

import com.example.entity.SysUser;

/**
 * @Author:congguangbo
 * @Date:2020/3/8 15:53
 */
public interface SysUserDao {
    public SysUser queryUserByName(String userName);
}
