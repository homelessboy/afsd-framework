package me.afsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: afsd
 * Date: 2016/1/21
 * Time: 14:26
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "hello";
    }
}
