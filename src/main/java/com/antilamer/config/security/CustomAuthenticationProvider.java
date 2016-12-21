package com.antilamer.config.security;

import com.antilamer.dao.UserDAO;
import com.antilamer.entity.UserEntity;
import com.antilamer.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenticationProvider
//        implements AuthenticationProvider
{
//
//    @Autowired
//    UserDAO userDAO;
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        List<GrantedAuthority> grantedAuths = new ArrayList<>();
//        UserEntity userDTO = userDAO.getByUsername(authentication.getPrincipal().toString());
//        grantedAuths.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + userDTO.getRole().getRole()));
//        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }

}