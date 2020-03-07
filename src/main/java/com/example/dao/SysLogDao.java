package com.example.dao;

import com.example.entity.SysLog;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogDao {
    public void save (SysLog syslog);
}
