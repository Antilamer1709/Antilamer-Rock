package com.antilamer.service;

import com.antilamer.beans.UserLoginBean;
import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.exeptions.ValidationExeption;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserBO {
    void registerUser(UserRegistrationBean userRegistrationBean) throws ValidationExeption;

    ResponseEntity<?> login(UserLoginBean loginBean, HttpServletRequest req) throws Exception;
}
