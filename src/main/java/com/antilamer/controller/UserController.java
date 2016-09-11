package com.antilamer.controller;

import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.exeptions.ServerExeption;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.service.UserBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserBO userBO;

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveAdvertisement(@RequestBody UserRegistrationBean registrationBean) throws ValidationExeption {
        logger.info("*** registerUser()");
        if (registrationBean != null ) {
            userBO.registerUser(registrationBean);
            return;
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }
}
