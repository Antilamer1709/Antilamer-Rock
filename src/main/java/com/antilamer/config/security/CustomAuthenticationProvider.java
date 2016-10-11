package com.antilamer.config.security;

import com.antilamer.dao.UserDAO;
import com.antilamer.model.UserDTO;
import com.antilamer.service.UserBOImpl;
import com.antilamer.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger = Logger.getLogger(UserBOImpl.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        UserDTO userDTO = userDAO.getByUsername(authentication.getPrincipal().toString());
        grantedAuths.add(new SimpleGrantedAuthority(userDTO.getRole().getRole()));
        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}