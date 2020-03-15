package com.example.service;

import com.example.entity.SysLog;
import com.example.entity.SysMenu;
import com.example.entity.SysUser;

import java.util.List;
import java.util.Set;

public interface SysMenuService {
    public Set<String> selectPermsByUserId(Long userId);
    public List<SysMenu> selectMenusByUser(SysUser user);
}
