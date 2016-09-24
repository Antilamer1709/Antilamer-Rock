package com.antilamer.service;

import com.antilamer.beans.UserLoginBean;
import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.config.WebSecurityConfig;
import com.antilamer.dao.UserDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service(value = "userBO")
public class UserBOImpl implements UserBO{
    private static Logger logger = Logger.getLogger(UserBOImpl.class);


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Override
    @Transactional
    public void registerUser(UserRegistrationBean userRegistrationBean) throws ValidationExeption {
        logger.info("*** registerUser() start");
        validateUserRegistration(userRegistrationBean);
        UserDTO userDTO = new UserDTO();
        initUserDTO(userDTO, userRegistrationBean);
        userDAO.persist(userDTO);
        logger.info("*** registerUser() end");
    }

    @Override
    @Transactional
    public void login(UserLoginBean loginBean, HttpServletRequest req) throws Exception {
        logger.info("*** login() start");
        UserDTO userDTO = userDAO.getByUsername(loginBean.getUsername());
        if (userDTO == null || !userDTO.getPassword().equals(loginBean.getPassword())){
            throw new ValidationExeption("Username or password is incorrect!");
        }
        webSecurityConfig.login(req, loginBean.getUsername(), loginBean.getPassword());
        logger.info("*** login() end");
    }

    private void validateUserRegistration(UserRegistrationBean bean){
        UserDTO userDTO;
        if (bean.getUsername() != null){
            userDTO = userDAO.getByUsername(bean.getUsername());
            if (userDTO != null && userDTO.getUsername() != null){
                throw new ValidationExeption("User with this username is already exist!");
            }
        }
        if (bean.getUsername() != null){
            userDTO = userDAO.getByEmail(bean.getEmail());
            if (userDTO != null && userDTO.getEmail() != null){
                throw new ValidationExeption("User with this email is already exist!");
            }
        }
        if (!bean.getPassword().equals(bean.getConfirmPassword())){
            throw new ValidationExeption("Passwords are not matching!");
        }
    }

    private void initUserDTO(UserDTO userDTO, UserRegistrationBean bean){
        userDTO.setUsername(bean.getUsername());
        userDTO.setEmail(bean.getEmail());
        userDTO.setPassword(bean.getPassword());
        userDTO.setRegistrationDate(new Date());
    }

}
