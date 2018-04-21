package com.jxau.soft.webchat.service;

import com.jxau.soft.webchat.pojo.TbRecord;

import java.util.List;

/**
 * @author Hanoch
 */
public interface ChatRecordsService {

    /**
     * 根据id查询单条聊天记录
     *
     * @param recordId 记录id（32位uuid）
     * @return TbRecord
     */
    TbRecord getRecordById(String recordId);

    /**
     * 获取两用户的聊天记录
     * 进行双向查询，获取双方数据，展示在聊天
     *
     * @param fromUserId 发送者id
     * @param toUserId   接收者id
     * @return list
     */
    List<TbRecord> getRecords(String fromUserId, String toUserId);

    /**
     * 添加一条会话记录
     *
     * @param record 会话记录
     * @return int
     */
    int addOneRecord(TbRecord record);

    /**
     * 更新会话记录状态
     *
     * @param fromUserId
     * @param toUserId
     * @return
     */
    int updateRecords(String fromUserId, String toUserId);
}
