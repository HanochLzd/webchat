package com.jxau.soft.webchat.service;


import com.jxau.soft.webchat.pojo.TbUser;

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
     * 更新user信息
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
