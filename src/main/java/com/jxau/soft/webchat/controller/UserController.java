package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.pojo.TbUser;
import com.jxau.soft.webchat.service.LogService;
import com.jxau.soft.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Hanoch
 */
@Controller
@SessionAttributes("userid")
public class UserController {

    private TbUser user;

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    /**
     * 聊天主页
     *
     * @return
     */
    @RequestMapping("/chat")
    public ModelAndView getIndex() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 显示个人信息
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/{userid}/info", method = RequestMethod.GET)
    public ModelAndView showOneDetails(@PathVariable("userid") String userid) {
        ModelAndView view = new ModelAndView("information");
        TbUser user = userService.queryUserByUserid(userid);
        view.addObject("user", user);
        return view;
    }

    /**
     * 显示个人信息设置
     *
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping(value = "/{userid}/config", method = RequestMethod.GET)
    public String userConfig(@PathVariable("userid") String userid, HttpServletRequest request) {
        TbUser user = userService.queryUserByUserid(userid);
        request.setAttribute("user", user);
        return "info-setting";
    }

    /**
     * 更新个人信息
     */

}
