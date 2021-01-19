package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import com.grhtest.pojo.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 如果使用的是@Controller注解
 * 需要再方法上面添加一个@ResponseBody注解
 * 其中ResponseBody表示的是返回的结果全部以json字符串为主，或者是json对象
 * @author grh
 */
@RestController
public class UserController {
    @RequestMapping("/getHello")
    public User getHello() {
        User user = new User();
        user.setName("imooc");
        user.setPassword("imooc");
        user.setAge(25);
        user.setBirthday(new Date());
        user.setDesc("how are you!");
        return  user;
    }

    @RequestMapping("/getJson")
    public GrhJsonResult getJson() {
        User user = new User();
        user.setName("json字符串");
        user.setPassword("你好呀！");
        user.setAge(55);
        user.setBirthday(new Date());
        user.setDesc("json对象");
        return GrhJsonResult.ok(user);
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map) {

        User us = new User();
        us.setName("Grh");
        us.setAge(19);
        us.setPassword("1231231");
        us.setBirthday(new Date());
        us.setDesc("<font color='green'><b>hello</b></font>");

        map.addAttribute("user", us);

        User u1 = new User();
        u1.setAge(22);
        u1.setName("imooc");
        u1.setPassword("12312313");
        u1.setBirthday(new Date());
        u1.setDesc("hello");

        return "thymeleaf/test";
    }

}
