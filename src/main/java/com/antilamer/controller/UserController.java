package com.antilamer.controller;

import com.antilamer.dto.user.LoggedUserDTO;
import com.antilamer.dto.user.UserLoginDTO;
import com.antilamer.dto.user.UserRegistrationDTO;
import com.antilamer.exeptions.ServerExeption;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.service.UserBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    public void registerUser(@RequestBody UserRegistrationDTO registrationDTO) throws ValidationExeption {
        logger.info("*** registerUser()");
        if (registrationDTO != null) {
            userBO.registerUser(registrationDTO);
            return;
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO, HttpServletResponse response) throws Exception {
        logger.info("*** loginBean()");
        if (loginDTO != null) {
            return userBO.login(loginDTO, response);
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "currentUser", method = RequestMethod.POST)
    public
    @ResponseBody
    LoggedUserDTO currentUser(HttpServletRequest request) throws Exception {
        logger.info("*** currentUser()");
        return userBO.currentUser(request);
    }

    @RequestMapping(value="logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        logger.info("*** logout()");
        return userBO.logout(request, response);
    }

    @RequestMapping(value = "restorePassword", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerUser(@RequestBody String username) throws MessagingException {
        logger.info("*** restorePassword() for username: " + username);
        userBO.restoreUserPassword(username);
    }
}
