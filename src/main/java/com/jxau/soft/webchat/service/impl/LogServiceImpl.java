package com.jxau.soft.webchat.service.impl;

import com.jxau.soft.webchat.mapper.TbLogMapper;
import com.jxau.soft.webchat.pojo.TbLog;
import com.jxau.soft.webchat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hanoch
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private TbLogMapper logMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(TbLog log) {
        logMapper.insert(log);
    }
}
