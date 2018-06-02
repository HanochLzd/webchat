package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.enums.WordDefined;
import com.jxau.soft.webchat.po.TbUser;
import com.jxau.soft.webchat.service.LogService;
import com.jxau.soft.webchat.service.UserService;
import com.jxau.soft.webchat.utils.LogUtil;
import com.jxau.soft.webchat.utils.NetUtil;
import com.jxau.soft.webchat.utils.UploadUtil;
import com.jxau.soft.webchat.vo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * @author Hanoch
 */
@Controller
@SessionAttributes("userid")
public class UserController {

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
    public ModelAndView getIndex(@SessionAttribute("userid")String userid,HttpServletRequest request) {
        ModelAndView model = new ModelAndView("index");
        //加载好友列表
        List<Friend> friends = userService.queryAllFriends(userid);
        System.err.println(friends);
        request.setAttribute("friends",friends);
        return model;
    }

    /**
     * 显示个人信息
     *
     * @param userid        userid
     * @param sessionUserid sessionUserid
     * @return ModelAndView
     */
    @RequestMapping(value = "/{userid}/info", method = RequestMethod.GET)
    public ModelAndView showOneDetails(@PathVariable("userid") String userid, @SessionAttribute("userid") String sessionUserid) {
        ModelAndView view = null;
        if (sessionUserid.equals(userid)) {
            view = new ModelAndView("information");
            TbUser user = userService.queryUserByUserid(userid);
            view.addObject("user", user);
        }
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
    public String userConfig(@PathVariable("userid") String userid, HttpServletRequest request,
                             @SessionAttribute("userid") String sessionUserid) {
        if (sessionUserid.equals(userid)) {
            TbUser user = userService.queryUserByUserid(userid);
            request.setAttribute("user", user);
            return "info-setting";
        }
        return "redirect:/" + sessionUserid + "/config";
    }

    /**
     * 更新个人信息
     *
     * @param userid        userid
     * @param sessionUserid sessionUserid
     * @param user          user
     * @param attributes    RedirectAttributes
     * @param netUtil       netUtil
     * @param request       HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/{userid}/update", method = RequestMethod.POST)
    public String updateUser(@PathVariable("userid") String userid, @SessionAttribute("userid") String sessionUserid,
                             TbUser user, RedirectAttributes attributes, NetUtil netUtil, HttpServletRequest request) {
        if (!user.getUserId().equals(sessionUserid)) {
            attributes.addFlashAttribute("error", "非法更改！");
        } else {
            int flag = userService.update(user);
            if (flag > 0) {
                logService.insert(LogUtil.setLog(userid, new Date(), WordDefined.LOG_TYPE_UPDATE.getWordDefined(),
                        WordDefined.LOG_DETAIL_UPDATE_PROFILE.getWordDefined(), netUtil.getIpAddress(request)));
                attributes.addFlashAttribute("message", "[" + userid + "]资料更新成功!");
            } else {
                attributes.addFlashAttribute("error", "[" + userid + "]资料更新失败!");
            }
        }
        return "redirect:/{userid}/config";
    }

    /**
     * 上传头像
     *
     * @param userid     userid
     * @param request    HttpServletRequest
     * @param attributes RedirectAttributes
     * @param netUtil    netUtil
     * @return String
     */
    @RequestMapping(value = "/{userid}/upload")
    public String upload(@PathVariable("userid") String userid, HttpServletRequest request,
                         RedirectAttributes attributes, NetUtil netUtil) {
        try {
            //获取文件上传路径
            String fileUrl = UploadUtil.upload(request, "upload", userid);
            TbUser user = userService.queryUserByUserid(userid);
            user.setUserProfilehead(fileUrl);
            int updateState = userService.update(user);
            if (updateState > 0) {
                logService.insert(LogUtil.setLog(userid, new Date(), WordDefined.LOG_TYPE_UPDATE.getWordDefined(),
                        WordDefined.LOG_DETAIL_UPDATE_PROFILEHEAD.getWordDefined(), netUtil.getIpAddress(request)));
                attributes.addFlashAttribute("message", "[" + userid + "]头像更新成功!");
            } else {
                attributes.addFlashAttribute("error", "[" + userid + "]头像更新失败!");
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "[" + userid + "]头像更新失败!");
        }
        return "redirect:/{userid}/config";
    }

    /**
     * 显示头像
     *
     * @param userid   userid
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/{userid}/head")
    public void head(@PathVariable("userid") String userid, HttpServletRequest request, HttpServletResponse response) {
        TbUser user = userService.queryUserByUserid(userid);
        String path = user.getUserProfilehead();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String headImagePath = rootPath + path;
        response.setContentType("image/jpeg;charset=UTF-8");
        //response输出流
        ServletOutputStream outputStream = null;
        //文件输入流
        FileInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(headImagePath);
            byte[] buffer = new byte[1024];
            int i;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert outputStream != null;
                outputStream.flush();
                outputStream.close();
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 修改密码
     *
     * @param userid  userid
     * @param oldpass 旧密码
     * @param newpass 新密码
     * @return String
     */
    @RequestMapping(value = "{userid}/pass", method = RequestMethod.POST)
    public String changePassword(@PathVariable("userid") String userid, String oldpass, String newpass, RedirectAttributes attributes,
                                 NetUtil netUtil, HttpServletRequest request) {
        TbUser user = userService.queryUserByUserid(userid);
        if (oldpass.equals(user.getPassword())) {
            user.setPassword(newpass);
            int updateSatus = userService.update(user);
            if (updateSatus > 0) {
                logService.insert(LogUtil.setLog(userid, new Date(), WordDefined.LOG_TYPE_UPDATE.getWordDefined(),
                        WordDefined.LOG_DETAIL_UPDATE_PASSWORD.getWordDefined(), netUtil.getIpAddress(request)));
                attributes.addFlashAttribute("message", "[" + userid + "]密码更新成功!");
            } else {
                attributes.addFlashAttribute("error", "[" + userid + "]密码更新失败!");
            }
        } else {
            attributes.addFlashAttribute("error", "密码错误!");
        }
        return "redirect:/{userid}/config";
    }
}
