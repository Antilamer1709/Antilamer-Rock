package com.antilamer.service;

import com.antilamer.beans.UserRegistrationBean;
import com.antilamer.exeptions.ValidationExeption;

public interface UserBO {
    void registerUser(UserRegistrationBean userRegistrationBean) throws ValidationExeption;
}
