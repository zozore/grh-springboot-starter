package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author grh
 */
@Controller
@RequestMapping("err")
public class ErrorController {

    @RequestMapping("/error")
    public String error() {
        int a = 1 / 0;
        return "thymeleaf/error";
    }

    @RequestMapping("/ajaxError")
    public String ajaxError() {
        return "thymeleaf/ajaxError";
    }

    @RequestMapping("/getAjax")
    @ResponseBody
    public GrhJsonResult getAjax() {
        int a = 1 / 0;
        return GrhJsonResult.ok();
    }
}
