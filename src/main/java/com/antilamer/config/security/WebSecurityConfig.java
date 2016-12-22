package com.antilamer.config.security;

import com.antilamer.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;
//
//    @Autowired
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .authenticationProvider(this.customAuthenticationProvider);
//    }

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,role_id from USERS where username=?")
                .authoritiesByUsernameQuery(
                        "SELECT USERS.username, ROLES.role FROM USERS JOIN ROLES ON ROLES.id = USERS.role_id where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        http
                .authorizeRequests()
                    .antMatchers(Constants.RESOURCES_PERMISSION_ALL).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/").failureUrl("/")
                    .loginProcessingUrl("/user/login/")
                    .usernameParameter("username").passwordParameter("password")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

}
