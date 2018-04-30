package com.jxau.soft.webchat.service.impl;

import com.jxau.soft.webchat.mapper.TbUserMapper;
import com.jxau.soft.webchat.pojo.TbUser;
import com.jxau.soft.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hanoch
 */
@Service
public class UserServiceImpl implements UserService {

    private final TbUserMapper tbUserMapper;

    @Autowired
    public UserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    @Override
    public TbUser queryUserByUserid(String userid) {
        return tbUserMapper.selectByPrimaryKey(userid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(TbUser user) {
        return tbUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addOne(TbUser tbUser) {
        return tbUserMapper.insert(tbUser);
    }
}
