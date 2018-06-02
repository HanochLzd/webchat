package com.jxau.soft.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Hanoch
 */
@Controller
@RequestMapping(value = "")
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/help")
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/system-setting")
    public String system() {
        return "system-setting";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/user/list")
    public String userList(){
        return "user-list";
    }

}
