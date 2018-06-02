package com.jxau.soft.webchat.controller;

import com.jxau.soft.webchat.po.TbLog;
import com.jxau.soft.webchat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Hanoch
 */
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/admin/log")
    public String getLogs(HttpServletRequest request) {
        List<TbLog> list = logService.queryAllLogs();
        request.setAttribute("list", list);
        request.setAttribute("count",list.size());
        return "log";
    }
}
