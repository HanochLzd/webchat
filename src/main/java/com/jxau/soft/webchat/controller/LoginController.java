package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.enums.WordDefined;
import com.jxau.soft.webchat.pojo.TbLog;
import com.jxau.soft.webchat.pojo.TbUser;
import com.jxau.soft.webchat.service.LogService;
import com.jxau.soft.webchat.service.UserService;
import com.jxau.soft.webchat.utils.LogUtil;
import com.jxau.soft.webchat.utils.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @author Hanoch
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     *
     * @param userid     用户名
     * @param password   密码
     * @param session    httpsession
     * @param attributes attributes
     * @param netUtil    工具类
     * @param request    HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userid, String password, HttpSession session, RedirectAttributes attributes,
                        NetUtil netUtil, HttpServletRequest request) {
        TbUser user = userService.queryUserByUserid(userid);
        if (user == null) {
            attributes.addFlashAttribute("error", WordDefined.LOGIN_USERID_ERROR.getWordDefined());
            return "redirect:/user/login";
        } else {
            if (!user.getPassword().equals(password)) {
                attributes.addFlashAttribute("error", WordDefined.LOGIN_PASSWORD_ERROR.getWordDefined());
                return "redirect:/user/login";
            } else {
                if (user.getUserStatus() == null || user.getUserStatus() != 1) {
                    attributes.addFlashAttribute("error", WordDefined.LOGIN_USERID_DISABLED.getWordDefined());
                    return "redirect:/user/login";
                } else {
                    logService.insert(LogUtil.setLog(userid, new Date(), WordDefined.LOG_TYPE_LOGIN.getWordDefined(),
                            WordDefined.LOG_DETAIL_USER_LOGIN.getWordDefined(), netUtil.getIpAddress(request)));
                    session.setAttribute("userid", userid);
                    session.setAttribute("login_status", true);
                    user.setUserLastTime(new Date());
                    userService.update(user);
                    attributes.addFlashAttribute("message", WordDefined.LOGIN_SUCCESS.getWordDefined());
                    return "redirect:/chat";
                }
            }
        }
    }


    /**
     * 注销
     *
     * @param session    用户会话session
     * @param attributes 重定向
     * @return string
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, RedirectAttributes attributes) {
        session.removeAttribute("userid");
        session.removeAttribute("login_status");
        attributes.addFlashAttribute("message", WordDefined.LOGOUT_SUCCESS.getWordDefined());
        return "redirect:/user/login";
    }
}
