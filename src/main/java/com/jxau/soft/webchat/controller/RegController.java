package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.enums.WordDefined;
import com.jxau.soft.webchat.po.TbUser;
import com.jxau.soft.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * @author Hanoch
 */
@Controller
@RequestMapping(value = "")
public class RegController {
    /**
     * 1.验证注册信息
     * 用户名合法性
     * 密码合法性
     * 昵称合法性
     * 年龄选择
     * 头像注册后补全信息
     */

    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册
     *
     * @param userid     userid
     * @param pwd        pwd
     * @param nickName   nickName
     * @param userSex    userSex
     * @param attributes attributes
     * @return String
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("userId") String userid, @RequestParam("password") String pwd,
                           @RequestParam("userNickName") String nickName, @RequestParam("userSex") Integer userSex,
                           RedirectAttributes attributes) {
        TbUser queryUser = userService.queryUserByUserid(userid);
        if (queryUser != null) {
            attributes.addFlashAttribute("error", WordDefined.REG_USERID_REGISTERED.getWordDefined());
            return "redirect:/register";
        }
        TbUser user = setUser(userid, pwd, nickName, userSex);
        userService.addOne(user);
        attributes.addFlashAttribute("message", WordDefined.REG_SUCCESS.getWordDefined());
        attributes.addFlashAttribute("user", user);
        return "redirect:/register";
    }

    /**
     * 拼装user
     *
     * @param userid   用户名
     * @param pwd      密码
     * @param nickName 昵称
     * @param userSex  性别
     * @return TBUser
     */
    private TbUser setUser(String userid, String pwd, String nickName, Integer userSex) {
        TbUser user = new TbUser();
        user.setUserId(userid);
        user.setPassword(pwd);
        user.setUserNickName(nickName);
        user.setUserSex(userSex);
        user.setUserCreateTime(new Date());
        user.setUserStatus(1);
        return user;
    }
}
