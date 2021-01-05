package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import com.grhtest.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grh
 */
@RestController
public class HelloController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello grh! This is your first spirngBoot Project";
    }

    @RequestMapping("/getResource")
    public GrhJsonResult getResource() {
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        return GrhJsonResult.ok(bean);
    }
    @RequestMapping("/getResource1")
    public Resource getResource1() {
        return resource;
    }
}
