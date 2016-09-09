package com.antilamer.controller;

import com.antilamer.beans.BandBean;
import com.antilamer.dao.BandDAO;
import com.antilamer.model.BandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

}
