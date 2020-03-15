package com.example.service.impl;

import com.example.dao.SysRoleDao;
import com.example.entity.SysRole;
import com.example.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:congguangbo
 * @Date:2020/3/14 22:35
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRole> perms = sysRoleDao.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (!StringUtils.isEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
