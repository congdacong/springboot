package com.example.service;

import com.example.entity.SysRole;

import java.util.Set;

/**
 * @Author:congguangbo
 * @Date:2020/3/14 22:35
 */
public interface SysRoleService {
    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    public Set<String> selectRoleKeys(Long userId);

}
