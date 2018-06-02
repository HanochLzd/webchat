package com.jxau.soft.webchat.service.impl;

import com.jxau.soft.webchat.mapper.TbRelationMapper;
import com.jxau.soft.webchat.po.TbRelation;
import com.jxau.soft.webchat.po.TbRelationExample;
import com.jxau.soft.webchat.po.TbUser;
import com.jxau.soft.webchat.service.UserService;
import com.jxau.soft.webchat.vo.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.relation.Relation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class UserServiceImplTest {

    @Autowired
    private TbRelationMapper relationMapper;

    @Autowired
    private UserService userService;

    @Test
    public void queryAllFriends() {
//        List<Friend> friends =  userService.queryAllFriends("lzd");
//        System.out.println(friends);
        TbRelationExample example = new TbRelationExample();
        TbRelationExample.Criteria criteria = example.createCriteria();
        criteria.andOwnerUserEqualTo("lzd");
        List<TbRelation> relations = relationMapper.selectByExample(example);
        int relationsSize = relations.size();
        List<Friend> friends = new ArrayList<>();
        for (TbRelation relation : relations) {
            TbUser friendUser = userService.queryUserByUserid(relation.getFriendUser());
            Friend friend = new Friend();
            friend.setFriendId(relation.getFriendUser());
            friend.setFriendNickName(friendUser.getUserNickName());
            friend.setFriendProfileHead(friendUser.getUserProfilehead());
            friend.setFriendLoginStatus(friendUser.getUserLoginStatus());
            friend.setGroup(relation.getGroup());
            friends.add(friend);
        }
        System.err.println(friends);

    }
}