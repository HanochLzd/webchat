package com.jxau.soft.webchat.service;


import com.jxau.soft.webchat.pojo.TbUser;

/**
 * @author Hanoch
 */
public interface UserService {

    /**
     *根据id查询user
     * @param userid
     * @return
     */
    TbUser queryUserByUserid(String userid);

    /**
     * 更新user信息
     * @param user
     */
    int update(TbUser user);

    /**
     * 添加用户
     * @param tbUser
     * @return
     */
    int addOne(TbUser tbUser);
}
