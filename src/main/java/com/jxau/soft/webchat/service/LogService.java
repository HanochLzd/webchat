package com.jxau.soft.webchat.service;

import com.jxau.soft.webchat.po.TbLog;

import java.util.List;

/**
 * @author Hanoch
 */
public interface LogService {
    /**
     * insert log
     *
     * @param log
     */
    void insert(TbLog log);

    /**
     * all
     * @return
     */
    List<TbLog> queryAllLogs();
}
