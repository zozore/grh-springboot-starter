package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import com.grhtest.pojo.SysUser;
import com.grhtest.pojo.User;
import com.grhtest.utils.JsonUtils;
import com.grhtest.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author grh
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redisOperator;

    @RequestMapping("/test")
    public GrhJsonResult test() {
        strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~");

        SysUser user = new SysUser();
        user.setUserId("29292");
        user.setUserName("imooc");
        user.setBirthday(new Date());
        user.setAge(12);
        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

        SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);

        return GrhJsonResult.ok(jsonUser);
    }

    @RequestMapping("/getJsonList")
    public GrhJsonResult getJsonList() {
        User u1 = new User();
        u1.setName("小红1号");
        u1.setBirthday(new Date());
        u1.setAge(11);

        User u2 = new User();
        u2.setName("小红2号");
        u2.setBirthday(new Date());
        u2.setAge(15);

        User u3 = new User();
        u3.setName("小红3号");
        u3.setBirthday(new Date());
        u3.setAge(199);

        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        redisOperator.set("json:info:userlist", JsonUtils.objectToJson(list), 2000);
        String userListJson = redisOperator.get("json:info:userlist");
        List<User> result = JsonUtils.jsonToList(userListJson, User.class);

        return GrhJsonResult.ok(result);
    }
}
