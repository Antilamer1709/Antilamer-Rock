package com.antilamer.dao;

import com.antilamer.entity.UserEntity;

public interface UserDAO extends AbstractJpaDAO<UserEntity> {
    UserEntity getByUsername(String username);
    UserEntity getByEmail(String email);
}
