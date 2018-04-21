package com.jxau.soft.webchat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxau.soft.webchat.mapper.TbRecordMapper;
import com.jxau.soft.webchat.pojo.TbRecord;
import com.jxau.soft.webchat.pojo.TbRecordExample;
import com.jxau.soft.webchat.service.UserService;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class ChatRecordsServiceImplTest {

    @Autowired
    private TbRecordMapper recordMapper;

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void getRecordById() {
        System.out.println(UuidUtil.getTimeBasedUuid().toString());
    }

    @org.junit.Test
    public void getRecords() {
        TbRecordExample recordExample = new TbRecordExample();
        // TbRecordExample.Criteria criteria  = recordExample.createCriteria();
        // criteria.andFromUserIdEqualTo("hanoch");
        // criteria.andToUserIdEqualTo("lzd");

        recordExample.or().andFromUserIdEqualTo("hanoch").andToUserIdEqualTo("lzd");
        recordExample.or().andFromUserIdEqualTo("lzd").andToUserIdEqualTo("hanoch");
        recordExample.setOrderByClause("record_create_time ASC");
        System.out.println(recordMapper.selectByExample(recordExample));
        /**
         * 按recordCreateTime的升序排序得出的结果
         * 双向会话记录
         * [
         *  TbRecord{
         *      recordId='004672170a594a66879b439f6864cea1',
         *      fromUserId='hanoch',
         *      toUserId='lzd',
         *      content='nihao!',
         *      recordCreateTime=Sun Apr 01 00:00:00 CST 2018
         *  },
         *  TbRecord{
         *      recordId='004672170a594a66879b439f6864cea2',
         *      fromUserId='lzd',
         *      toUserId='hanoch',
         *      content='enen',
         *      recordCreateTime=Sun Apr 01 00:00:04 CST 2018
         *  }
         *
         *  ]
         */
        List<TbRecord> list = recordMapper.selectByExample(recordExample);

        //list转JSONObjec
        JSONArray recordsJSON = (JSONArray) JSONArray.toJSON(list);
        for (int i = 0; i < recordsJSON.size(); i++) {
            JSONObject record = recordsJSON.getJSONObject(i);
            String userNickName = userService.queryUserByUserid(record.getString("fromUserId")).getUserNickName();
            record.put("userNickName", userNickName);
        }
        System.out.println(recordsJSON.toJSONString());
        /**
         * [
         * {
         *      "recordCreateTime":1522512000000,
         *      "recordId":"004672170a594a66879b439f6864cea1",
         *      "fromUserId":"hanoch",
         *      "userNickName":"hanoch",
         *      "toUserId":"lzd",
         *      "content":"nihao!"
         * },
         * {
         *      "recordCreateTime":1522512004000,
         *      "recordId":"004672170a594a66879b439f6864cea2",
         *      "fromUserId":"lzd",
         *      "userNickName":"Amayadream",
         *      "toUserId":"hanoch",
         *      "content":"enen"
         * }
         * ]
         */

        long time = Long.parseLong("1522512000000");
        Date date2 = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        //输出结果为  2017*03*22//20**46**16
        System.out.println(dateFormat.format(date2));
        System.out.println(date2);


        System.out.println(new Date().toString());
        System.out.println(new Date().toString());
        // System.out.println(new Date("2018-04-15 12:11:51").toString());

        // System.out.println(dateFormat.parse("2018-04-15 12:11:51"));

        try {
            System.out.println(dateFormat.parse("2018-04-15 12:11:51"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @org.junit.Test
    public void updateRecords() {
        //创建组合查询条件
        TbRecordExample recordExample = new TbRecordExample();
        //拼装where组合条件
        recordExample.or().andFromUserIdEqualTo("lzd").andToUserIdEqualTo("hanoch");
        recordExample.or().andFromUserIdEqualTo("hanoch").andToUserIdEqualTo("lzd");
        TbRecord record = new TbRecord();
        record.setRecordStatus(2);

        System.out.println(recordMapper.updateByExampleSelective(record, recordExample));
    }
}