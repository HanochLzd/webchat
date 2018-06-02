package com.jxau.soft.webchat.service.impl;

import com.jxau.soft.webchat.mapper.TbLogMapper;
import com.jxau.soft.webchat.po.TbLog;
import com.jxau.soft.webchat.po.TbLogExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class LogServiceImplTest {

    @Autowired
    private TbLogMapper logMapper;

    @Test
    public void queryAllLogs() {
        TbLogExample logExample = new TbLogExample();
        List<TbLog> list = logMapper.selectByExample(logExample);
        System.err.println(list);
    }
}