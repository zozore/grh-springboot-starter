package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import com.grhtest.pojo.User;
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
}
