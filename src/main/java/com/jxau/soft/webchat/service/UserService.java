package com.jxau.soft.webchat.service;


import com.jxau.soft.webchat.po.TbUser;

/**
 * @author Hanoch
 */
public interface UserService {

    /**
     * 根据id查询user
     *
     * @param userid userid
     * @return TbUser
     */
    TbUser queryUserByUserid(String userid);

    /**
     * 更新user信息（个人资料、用户账号状态、用户登录状态）
     *
     * @param user user
     * @return int
     */
    int update(TbUser user);

    /**
     * 添加用户
     *
     * @param tbUser tbUser
     * @return int
     */
    int addOne(TbUser tbUser);
}
