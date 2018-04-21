/*
  本人擅长Ai、Fw、Fl、Br、Ae、Pr、Id、Ps、Au等软件的安装、破解与卸载
  精通CSS、JavaScript、PHP、ASP、C、C++、C#、Java、Ruby、Perl、Lisp、Python、Objective-C、Actionscript、Pascal等单词的拼写
  熟悉Windows、Linux、Mac、Android、IOS、WP、Fuchsia、ChromeOS等系统的开关机
 */
package com.jxau.soft.webchat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxau.soft.webchat.pojo.TbRecord;
import com.jxau.soft.webchat.service.ChatRecordsService;
import com.jxau.soft.webchat.service.UserService;
import com.vdurmont.emoji.EmojiParser;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 会话记录操作
 * 添加emoji解析
 *
 * @author Hanoch
 */
@Controller
@RequestMapping(value = "/user")
public class ChatRecordsController {

    @Autowired
    private ChatRecordsService chatRecordsService;

    @Autowired
    private UserService userService;

    /**
     * 查询会话记录,并将其标记为“已读”
     *
     * @param fromUserId fromUserId
     * @param toUserId   toUserId
     * @return json
     */
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    @ResponseBody
    public String queryRecords(String fromUserId, String toUserId) {
        List<TbRecord> records = chatRecordsService.getRecords(fromUserId, toUserId);
        // list转JSONArray
        if (!records.isEmpty()) {
            JSONArray recordsJSON = (JSONArray) JSONArray.toJSON(records);
            for (int i = 0; i < recordsJSON.size(); i++) {
                JSONObject record = recordsJSON.getJSONObject(i);
                String userNickName = userService.queryUserByUserid(record.getString("fromUserId")).getUserNickName();
                //加入“昵称”
                record.put("userNickName", userNickName);
                //更改日期格式
                record.put("recordCreateTime", setFormateDate(record.getDate("recordCreateTime")));
            }
            return EmojiParser.parseToUnicode(recordsJSON.toJSONString());
        } else {
            return "error";
        }
    }

    /**
     * 漫游一条记录
     *
     * @param fromUserId       fromUserId
     * @param toUserId         toUserId
     * @param content          content
     * @param recordCreateTime recordCreateTime
     * @return json
     */
    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    @ResponseBody
    public String addRecord(String fromUserId, String toUserId, String content, String recordCreateTime) {
        /*
         * fromUserId: lzd
         * toUserId: hanoch
         * content: 😄哈哈
         * recordCreateTime: 2018-04-15 12:00:45
         */
        try {
            int status = chatRecordsService.addOneRecord(getRecord(fromUserId, toUserId, content, recordCreateTime));
            return JSON.toJSONString("sucess:" + status);
        } catch (ParseException e) {
            return "error";
        }
    }

    /**
     * 拼装record对象
     *
     * @param fromUserId       fromUserId
     * @param toUserId         toUserId
     * @param content          content
     * @param recordCreateTime recordCreateTime
     * @return TbRecord
     * @throws ParseException ParseException
     */
    private TbRecord getRecord(String fromUserId, String toUserId, String content, String recordCreateTime) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        TbRecord record = new TbRecord();
        record.setContent(EmojiParser.parseToAliases(content));
        record.setFromUserId(fromUserId);
        record.setRecordCreateTime(dateFormat.parse(recordCreateTime));
        record.setToUserId(toUserId);
        record.setRecordId(UuidUtil.getTimeBasedUuid().toString().replace("-", ""));
        return record;
    }

    /**
     * 转换成指定日期格式
     *
     * @param recordCreateTime recordCreateTime
     * @return yyyy-MM-dd kk:mm:ss
     */
    private String setFormateDate(Date recordCreateTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return dateFormat.format(recordCreateTime);
    }

}
