package com.grhtest.controller.interceptor;

import com.grhtest.pojo.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author grh
 */
@RestController
@RequestMapping("two")
public class TwoController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "imooc22");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map) {
        User user = new User();
        user.setAge(18);
        user.setName("manager");
        user.setPassword("123456");
        user.setBirthday(new Date());

        map.addAttribute("user", user);

        User u1 = new User();
        u1.setAge(19);
        u1.setName("imooc");
        u1.setPassword("123455");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setBirthday(new Date());
        u2.setName("iiiii");
        u2.setPassword("823872872");
        u2.setAge(88);

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(u1);
        list.add(u2);

        map.addAttribute("list", list);

        return "thymeleaf/test";
    }

    @PostMapping("postForm")
    public String postForm(User user){
        System.out.println(user.getName());
        return "redirect:/th/test";
    }
}
