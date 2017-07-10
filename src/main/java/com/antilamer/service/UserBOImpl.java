package com.antilamer.service;

import com.antilamer.dao.RememberMeDAO;
import com.antilamer.dto.user.LoggedUserDTO;
import com.antilamer.dto.user.UserLoginDTO;
import com.antilamer.dto.user.UserRegistrationDTO;
import com.antilamer.dao.RoleDAO;
import com.antilamer.dao.UserDAO;
import com.antilamer.entity.RememberMeEntity;
import com.antilamer.entity.UserEntity;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.entity.RoleEntity;
import com.antilamer.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;
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

    @Autowired
    private RememberMeDAO rememberMeDAO;

    @Autowired
    private MailBO mailBO;

    @Value("${token.valid.days}")
    private Integer tokenValidDays;

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
    public ResponseEntity<?> login(UserLoginDTO loginDTO,  HttpServletResponse response) throws Exception {
        logger.info("*** login() start");
        UserEntity userEntity = userDAO.getByUsername(loginDTO.getUsername().toLowerCase());
        if (userEntity != null && passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
            try {
                Authentication token = new UsernamePasswordAuthenticationToken(userEntity.getUsername(),
                        passwordEncoder.encode(loginDTO.getPassword()));
                Authentication auth = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.info("*** login() end for: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                logger.info("*** is rememberMe: " + loginDTO.getRememberMe());
                if (loginDTO.getRememberMe() != null && loginDTO.getRememberMe()){
                    rememberMe(loginDTO, response);
                }
                return new ResponseEntity<>(token, HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity<>(String.format("{\"error\": \"%s\"}", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        throw new ValidationExeption("Username or password is incorrect!");
    }

    @Override
    @Transactional
    public LoggedUserDTO currentUser(HttpServletRequest request) {
        logger.info("*** currentUser() start");
        checkRememberMe(request);
        LoggedUserDTO userDTO = new LoggedUserDTO();
        userDTO.setUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
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
    @Transactional
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            deleteRememberMe(getLoggedUser());
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @Override
    @Transactional
    public void restoreUserPassword(String login) throws MessagingException {
        UserEntity userEntity = userDAO.getByUsername(login);
        if (userEntity != null){
            Address[] addresses = InternetAddress.parse(userEntity.getEmail());
            String newPassword = generateNewPassword();
            persistNewPassword(userEntity, newPassword);
            deleteRememberMe(userEntity);
            String text = Constants.MAIL_RESTORE_PASS_TEXT + newPassword;
            mailBO.sendMail(addresses, Constants.MAIL_RESTORE_PASS_SUBCJECT, text);
        } else {
            throw new ValidationExeption("User with this username does not exist!");
        }
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

    private void rememberMe(UserLoginDTO loginDTO, HttpServletResponse response){
        RememberMeEntity rememberMeEntity = rememberMeDAO.findByLogin(loginDTO.getUsername());
        if (rememberMeEntity != null) {
            refreshCreationDate(rememberMeEntity);
        } else {
            saveUserData(loginDTO, response);
        }
    }

    private void saveUserData(UserLoginDTO loginDTO, HttpServletResponse response){
        logger.info("*** saveUserData() start");
        RememberMeEntity rememberMeEntity = new RememberMeEntity();
        initRememberMeEntity(rememberMeEntity, loginDTO);
        rememberMeDAO.persist(rememberMeEntity);
        Cookie cookie = new Cookie("rememberMeToken", rememberMeEntity.getToken());
        cookie.setMaxAge(tokenValidDays * 86400);  //86400 == 24 * 60 * 60
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("*** saveUserData() end, user token: " + rememberMeEntity.getToken());
    }

    private void initRememberMeEntity(RememberMeEntity rememberMeEntity, UserLoginDTO loginDTO){
        Date creationDate = new Date();
        rememberMeEntity.setLogin(loginDTO.getUsername());
        rememberMeEntity.setEncodedPassword(passwordEncoder.encode(loginDTO.getPassword()));
        rememberMeEntity.setToken(passwordEncoder.encode(creationDate.toString()));
        rememberMeEntity.setCreationDate(creationDate);
    }

    private void checkRememberMe(HttpServletRequest request){
        logger.info("*** checkRememberMe()");
        String rememberMeToken = getRememberMeToken(request);
        UserEntity userEntity = getLoggedUser();
        logger.info("*** checkRememberMe(), start for token: " + rememberMeToken);
        if (rememberMeToken != null && userEntity == null) {
            RememberMeEntity rememberMeEntity = rememberMeDAO.findByToken(rememberMeToken);
            if (isRememberMeEntityValid(rememberMeEntity)){
                authenticateUser(rememberMeEntity.getLogin(), rememberMeEntity.getEncodedPassword());
                refreshCreationDate(rememberMeEntity);
            }
        }
    }

    private String getRememberMeToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("rememberMeToken")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void refreshCreationDate(RememberMeEntity rememberMeEntity) {
        logger.info("*** refreshCreationDate(), token will be persist with new date");
        rememberMeEntity.setCreationDate(new Date());
        rememberMeDAO.persist(rememberMeEntity);
    }

    private boolean isRememberMeEntityValid(RememberMeEntity rememberMeEntity){
        if (rememberMeEntity != null) {
            Date now = new Date();
            //86400000 == 1000 * 60 * 60 * 24
            long daysFromCreation = (now.getTime() - rememberMeEntity.getCreationDate().getTime()) / 86400000;
            if (daysFromCreation < tokenValidDays){
                logger.info("*** isRememberMeEntityValid(), token is valid, days from creation: " + daysFromCreation);
                return true;
            } else {
                logger.info("*** isRememberMeEntityValid(), token is not valid, days from creation: " + daysFromCreation);
                return false;
            }
        } else {
            logger.info("*** isRememberMeEntityValid(), entity is null!");
            return false;
        }
    }

    private void authenticateUser(String login, String encodedPassword) {
        Authentication token = new UsernamePasswordAuthenticationToken(login, encodedPassword);
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void deleteRememberMe(UserEntity userEntity){
        logger.info("*** deleteRememberMe(), for userName: " + userEntity.getUsername());
        RememberMeEntity rememberMeEntity = rememberMeDAO.findByLogin(userEntity.getUsername());
        if (rememberMeEntity != null){
            rememberMeDAO.deleteById(rememberMeEntity.getId());
        }
    }

    private void persistNewPassword(UserEntity userEntity, String newPassword){
        String encodedPassword = passwordEncoder.encode(newPassword);
        userEntity.setPassword(encodedPassword);
        userDAO.persist(userEntity);
    }

    private String generateNewPassword(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(40, random).toString(32);
    }

}
