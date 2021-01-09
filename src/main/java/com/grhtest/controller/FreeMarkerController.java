package com.grhtest.controller;

import com.grhtest.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author grh
 */
@Controller
@RequestMapping("/ftl")
public class FreeMarkerController {

    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("resource", resource);
        return "freemarker/index";
    }

    @RequestMapping("/center")
    public String index() {
        return "freemarker/center/center";
    }
}
