package com.jxau.soft.webchat.utils;

import com.jxau.soft.webchat.pojo.TbLog;

import java.util.Date;
import java.util.UUID;

/**
 * log工具类
 *
 * @author Hanoch
 */
public class LogUtil {

    /**
     * 拼装TbLog属性
     *
     * @param userid userid
     * @param time   time
     * @param type   type
     * @param detail detail
     * @param ip     ip
     * @return TbLog
     */
    public static TbLog setLog(String userid, Date time, String type, String detail, String ip) {
        TbLog log = new TbLog();
        String id = UUID.randomUUID().toString().replace("-", "");
        log.setLogId(id);
        log.setLogUserId(userid);
        log.setLogCreateTime(time);
        log.setLogType(type);
        log.setLogDetail(detail);
        log.setIp(ip);
        return log;
    }
}
