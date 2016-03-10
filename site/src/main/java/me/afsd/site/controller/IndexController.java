package me.afsd.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: afsd
 * Date: 2016/1/21
 * Time: 14:26
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("test/{path}")
    public String index(@PathVariable String path){
        return path;
    }
}
