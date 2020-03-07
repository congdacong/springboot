package com.example.service.impl;

import com.example.dao.SysLogDao;
import com.example.entity.SysLog;
import com.example.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogDao sysLogDao;
    @Override
    public void save(SysLog syslog) {
        sysLogDao.save(syslog);
    }
}
