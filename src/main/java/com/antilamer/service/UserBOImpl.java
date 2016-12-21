package com.antilamer.service;

import com.antilamer.dto.user.LoggedUserDTO;
import com.antilamer.dto.user.UserLoginDTO;
import com.antilamer.dto.user.UserRegistrationDTO;
import com.antilamer.dao.RoleDAO;
import com.antilamer.dao.UserDAO;
import com.antilamer.entity.UserEntity;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.entity.RoleEntity;
import com.antilamer.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    public void registerUser(UserRegistrationDTO userRegistrationDTO) throws ValidationExeption {
        logger.info("*** registerUser() start");
        validateUserRegistration(userRegistrationDTO);
        UserEntity userEntity = new UserEntity();
        initUserEntity(userEntity, userRegistrationDTO);
        userDAO.persist(userEntity);
        logger.info("*** registerUser() end");
    }

    @Override
    @Transactional
    public ResponseEntity<?> login(UserLoginDTO loginDTO, HttpServletRequest req) throws Exception {
        logger.info("*** login() start");
        UserEntity userEntity = userDAO.getByUsername(loginDTO.getUsername().toLowerCase());
        if (userEntity != null && passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
            try {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().getRole());
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(authority);
                Authentication token = new UsernamePasswordAuthenticationToken(userEntity.getUsername(),
                        passwordEncoder.encode(loginDTO.getPassword()), authorities);
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
    public LoggedUserDTO currentUser() {
        logger.info("*** currentUser() start");
        LoggedUserDTO userDTO = new LoggedUserDTO();
        userDTO.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        userDTO.setLogged(!checkAnonymous(SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        userDTO.setRole(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString());
        logger.info("*** currentUser() end for " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userDTO;
    }

    @Override
    public UserEntity getLoggedUser() {
        return userDAO.getByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    private void validateUserRegistration(UserRegistrationDTO registrationDTO){
        UserEntity userEntity;
        if (registrationDTO.getUsername() != null){
            userEntity = userDAO.getByUsername(registrationDTO.getUsername());
            if (userEntity != null && userEntity.getUsername() != null){
                throw new ValidationExeption("User with this username is already exist!");
            }
        }
        if (registrationDTO.getUsername() != null){
            userEntity = userDAO.getByEmail(registrationDTO.getEmail());
            if (userEntity != null && userEntity.getEmail() != null){
                throw new ValidationExeption("User with this email is already exist!");
            }
        }
        if (registrationDTO.getEmail() == null){
            throw new ValidationExeption("Please, enter correct email!");
        }
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new ValidationExeption("Passwords are not matching!");
        }
    }

    private void initUserEntity(UserEntity userEntity, UserRegistrationDTO registrationDTO){
        userEntity.setUsername(registrationDTO.getUsername());
        userEntity.setEmail(registrationDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userEntity.setRegistrationDate(new Date());
        RoleEntity role = roleDAO.findById(1l);//Now user can get only USER role with id 1
        userEntity.setRole(role);
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
