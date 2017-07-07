package com.antilamer.dao;

import com.antilamer.entity.RememberMeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RememberMeDAOImpl extends AbstractJpaDAOImpl<RememberMeEntity> implements RememberMeDAO {

    @Override
    public RememberMeEntity findByToken(String token) {
        return findFirst("select r from RememberMeEntity r where r.token = ?1", token);
    }

    @Override
    public RememberMeEntity findByLogin(String login) {
        return findFirst("select r from RememberMeEntity r where r.login = ?1", login);
    }
}
