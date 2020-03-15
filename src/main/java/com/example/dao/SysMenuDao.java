package com.example.dao;

import com.example.entity.SysLog;
import com.example.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysMenuDao {
    public List<String> selectPermsByUserId(Long userId);
    public List<SysMenu> selectMenuNormalAll();
    public List<SysMenu> selectMenusByUserId(Long userId);
}
