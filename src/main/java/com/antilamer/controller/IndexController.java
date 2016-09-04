package com.antilamer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

/**
 * Created by antilamer on 03.09.16.
 */
//@RestController
@Controller
public class IndexController {
    //    @RequestMapping("/")
//    @RequestMapping(value = "/")
//    public String index() {
//        return "index";
//    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }
}
