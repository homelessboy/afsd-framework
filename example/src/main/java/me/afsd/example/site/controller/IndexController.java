package me.afsd.example.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("test/{path}")
    public String index(@PathVariable String path){
        return path;
    }
}
