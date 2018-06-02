package com.jxau.soft.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hanoch
 */
@Controller
public class LiveController {

    @RequestMapping("/live")
    public String getLive(@SessionAttribute("userid")String userid, HttpServletRequest request){
        request.setAttribute("userid",userid);
        return "live";
    }
}
