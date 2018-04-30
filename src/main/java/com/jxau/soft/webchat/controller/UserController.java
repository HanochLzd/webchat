package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.enums.WordDefined;
import com.jxau.soft.webchat.pojo.TbUser;
import com.jxau.soft.webchat.service.LogService;
import com.jxau.soft.webchat.service.UserService;
import com.jxau.soft.webchat.utils.LogUtil;
import com.jxau.soft.webchat.utils.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


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
     * @return ModelAndView
     */
    @RequestMapping("/chat")
    public ModelAndView getIndex() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 显示个人信息
     *
     * @param userid 用户id
     * @return ModelAndView
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
     * @param userid  userid
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/{userid}/config", method = RequestMethod.GET)
    public String userConfig(@PathVariable("userid") String userid, HttpServletRequest request) {
        TbUser user = userService.queryUserByUserid(userid);
        request.setAttribute("user", user);
        return "info-setting";
    }

    /**
     * 更新个人信息
     *
     * @param userid userid
     * @param sessionId sessionid
     * @param user user
     * @param attributes RedirectAttributes
     * @param netUtil netUtil
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/{userid}/update", method = RequestMethod.POST)
    public String updateUser(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionId,
                             TbUser user, RedirectAttributes attributes, NetUtil netUtil, HttpServletRequest request) {
        int flag = userService.update(user);
        if (flag > 0) {
            logService.insert(LogUtil.setLog(userid, new Date(), WordDefined.LOG_TYPE_UPDATE.getWordDefined(),
                    WordDefined.LOG_DETAIL_UPDATE_PROFILE.getWordDefined(), netUtil.getIpAddress(request)));
            attributes.addFlashAttribute("message", "[" + userid + "]资料更新成功!");
        } else {
            attributes.addFlashAttribute("error", "[" + userid + "]资料更新失败!");
        }
        return "redirect:/{userid}/config";
    }

    public String upload(){
        return "";
    }

}
