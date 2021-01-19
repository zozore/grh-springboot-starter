package com.grhtest.controller;

import com.grhtest.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author grh
 */
@Controller
@RequestMapping("th")
public class ThymeleafController {

    @RequestMapping("index")
    public String index(ModelMap map) {
        map.addAttribute("name", "thymeleaf-imooc");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map){
        User user = new User();
        user.setName("grh");
        user.setAge(18);
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setDesc("<font color='green'><b>hello</b></font>");
        map.addAttribute("user", user);
        return "thymeleaf/test";
    }

    @RequestMapping("postform")
    public String postform(User u) {
        System.out.println("User的Name是：" + u.getName());
        return "redirect:/th/test";
    }
}
