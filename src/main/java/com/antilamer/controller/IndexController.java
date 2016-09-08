package com.antilamer.controller;

import com.antilamer.beans.BandBean;
import com.antilamer.dao.BandDAO;
import com.antilamer.model.BandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

/**
 * Created by antilamer on 03.09.16.
 */
@Controller
public class IndexController {

    @Autowired
    private BandDAO bandDAO;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public BandBean test(){
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        BandDTO bandDTO = bandDAO.findById(1l);
        BandBean bean = new BandBean();
        if (bandDTO != null){
            bean.setId(bandDTO.getId());
        }
        System.out.println(bandDTO);
        System.out.println(bandDTO.getId());
        System.out.println("                   z                         ");
        return bean;
    }
}
