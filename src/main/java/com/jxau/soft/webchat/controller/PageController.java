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

    @RequestMapping(value = "/admin/log")
    public String log() {
        return "log";
    }

    @RequestMapping(value = "/include/header")
    public String header(){
        return "include/header";
    }

    @RequestMapping(value = "/include/sidebar")
    public String siderbar(){
        return "include/sidebar";
    }

    @RequestMapping(value = "/chatPage")
    public String chatPage(){
        return "chat";
    }
}
