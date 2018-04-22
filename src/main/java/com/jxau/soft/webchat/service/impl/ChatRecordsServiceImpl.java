package com.jxau.soft.webchat.service.impl;

import com.jxau.soft.webchat.mapper.TbRecordMapper;
import com.jxau.soft.webchat.pojo.TbRecord;
import com.jxau.soft.webchat.pojo.TbRecordExample;
import com.jxau.soft.webchat.service.ChatRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hanoch
 */
@Service
public class ChatRecordsServiceImpl implements ChatRecordsService {

    private final TbRecordMapper recordMapper;

    @Autowired
    public ChatRecordsServiceImpl(TbRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public TbRecord getRecordById(String recordId) {
        return recordMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public List<TbRecord> getRecords(String fromUserId, String toUserId) {
        //创建组合查询条件
        TbRecordExample recordExample = new TbRecordExample();
        //拼装where组合条件
        recordExample.or().andFromUserIdEqualTo(fromUserId).andToUserIdEqualTo(toUserId);
        recordExample.or().andFromUserIdEqualTo(toUserId).andToUserIdEqualTo(fromUserId);
        //按之间先后排序
        recordExample.setOrderByClause("record_create_time ASC");
        return recordMapper.selectByExample(recordExample);
    }

    @Override
    public int addOneRecord(TbRecord record) {
        return recordMapper.insert(record);
    }

    @Override
    public int updateRecords(String fromUserId, String toUserId) {
        //创建组合查询条件
        TbRecordExample recordExample = new TbRecordExample();
        //拼装where组合条件
        recordExample.or().andFromUserIdEqualTo(fromUserId).andToUserIdEqualTo(toUserId);
        //recordExample.or().andFromUserIdEqualTo(toUserId).andToUserIdEqualTo(fromUserId);

        TbRecord record = new TbRecord();
        record.setRecordStatus(2);
        return recordMapper.updateByExampleSelective(record, recordExample);
    }
}
