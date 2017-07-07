package com.antilamer.dao;

import com.antilamer.entity.RememberMeEntity;

public interface RememberMeDAO extends AbstractJpaDAO<RememberMeEntity>{
    RememberMeEntity findByToken(String token);

    RememberMeEntity findByLogin(String login);
}
