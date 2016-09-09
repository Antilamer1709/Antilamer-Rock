package com.antilamer.controller;

import com.antilamer.beans.BandBean;
import com.antilamer.beans.CommonSearchBean;
import com.antilamer.exeptions.ServerExeption;
import com.antilamer.service.BandBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BandController {

    private static Logger logger = Logger.getLogger(BandController.class);

    @Autowired
    private BandBO bandBO;


    @RequestMapping(value = "/getBand", method = RequestMethod.POST)
    public
    @ResponseBody
    BandBean getBand(@RequestBody CommonSearchBean searchBean) {
        logger.info("getBand()");
        if (searchBean != null && searchBean.getId() != null) {
            return bandBO.getBand(searchBean.getId());
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }
}