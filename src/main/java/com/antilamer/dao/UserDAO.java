package com.antilamer.dao;

import com.antilamer.model.UserDTO;

public interface UserDAO extends AbstractJpaDAO<UserDTO> {
    UserDTO getByUsername(String username);
    UserDTO getByEmail(String email);
}
