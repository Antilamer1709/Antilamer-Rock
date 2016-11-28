package com.antilamer.service;

import com.antilamer.beans.user.LoggedUserBean;
import com.antilamer.beans.user.UserLoginBean;
import com.antilamer.beans.user.UserRegistrationBean;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.UserDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserBO {
    void registerUser(UserRegistrationBean userRegistrationBean) throws ValidationExeption;

    ResponseEntity<?> login(UserLoginBean loginBean, HttpServletRequest req) throws Exception;

    LoggedUserBean currentUser();

    UserDTO getLoggedUser();

    String logout(HttpServletRequest request, HttpServletResponse response);
}
