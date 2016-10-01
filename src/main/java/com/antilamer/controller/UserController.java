package com.antilamer.controller;

import com.antilamer.beans.LoggedUserBean;
import com.antilamer.beans.UserLoginBean;
import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.exeptions.ServerExeption;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.service.UserBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (registrationBean != null) {
            userBO.registerUser(registrationBean);
            return;
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginBean loginBean, HttpServletRequest req) throws Exception {
        logger.info("*** loginBean()");
        if (loginBean != null) {
            return userBO.login(loginBean, req);
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "currentUser", method = RequestMethod.POST)
    public
    @ResponseBody
    LoggedUserBean login(HttpServletRequest req) throws Exception {
        logger.info("*** currentUser()");
        return userBO.currentUser();
    }

    @RequestMapping(value="logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        logger.info("*** logout()");
        return userBO.logout(request, response);
    }
}
