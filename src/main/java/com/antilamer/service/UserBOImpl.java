package com.antilamer.service;

import com.antilamer.beans.LoggedUserBean;
import com.antilamer.beans.UserLoginBean;
import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.dao.RoleDAO;
import com.antilamer.dao.UserDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.RoleDTO;
import com.antilamer.model.UserDTO;
import com.antilamer.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

@Service(value = "userBO")
public class UserBOImpl implements UserBO{
    private static Logger logger = Logger.getLogger(UserBOImpl.class);


    @Autowired
    private UserDAO userDAO;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> login(UserLoginBean loginBean, HttpServletRequest req) throws Exception {
        logger.info("*** login() start");
        UserDTO userDTO = userDAO.getByUsername(loginBean.getUsername());
        if (userDTO != null && passwordEncoder.matches(loginBean.getPassword(), userDTO.getPassword())) {
            try {
                Authentication token = new UsernamePasswordAuthenticationToken(loginBean.getUsername(),
                        passwordEncoder.encode(loginBean.getPassword()));
                Authentication auth = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.info("*** login() end for: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                return new ResponseEntity<>(token, HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity<>(String.format("{\"error\": \"%s\"}", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        throw new ValidationExeption("Username or password is incorrect!");
    }

    @Override
    public LoggedUserBean currentUser() {
        logger.info("*** currentUser() start");
        LoggedUserBean userBean = new LoggedUserBean();
        userBean.setUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        userBean.setLogged(!checkAnonymous(SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        userBean.setRole(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString());
        logger.info("*** currentUser() end for " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userBean;
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
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
        userDTO.setPassword(passwordEncoder.encode(bean.getPassword()));
        userDTO.setRegistrationDate(new Date());
        RoleDTO role = roleDAO.findById(1l);//Now user can get only USER role with id 1
        userDTO.setRole(role);
    }

    private boolean checkAnonymous(Collection<? extends GrantedAuthority> authorities){
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().equals(Constants.ROLE_ANONYMOUS)){
                return true;
            }
        }
        return false;
    }

}
