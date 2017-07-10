package com.antilamer.service;

import com.antilamer.dto.user.LoggedUserDTO;
import com.antilamer.dto.user.UserLoginDTO;
import com.antilamer.dto.user.UserRegistrationDTO;
import com.antilamer.entity.UserEntity;
import com.antilamer.exeptions.ValidationExeption;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserBO {
    void registerUser(UserRegistrationDTO userRegistrationDTO) throws ValidationExeption;

    ResponseEntity<?> login(UserLoginDTO loginDTO, HttpServletResponse response) throws Exception;

    LoggedUserDTO currentUser(HttpServletRequest request);

    UserEntity getLoggedUser();

    String logout(HttpServletRequest request, HttpServletResponse response);

    void restoreUserPassword(String login) throws MessagingException;
}
