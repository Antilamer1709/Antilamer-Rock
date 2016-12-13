package com.antilamer.dao;


import com.antilamer.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class UserDAOImpl extends AbstractJpaDAOImpl<UserEntity> implements UserDAO{
    @Override
    public UserEntity getByUsername(String username) {
        String sql =
                "select a"
                        + " from UserEntity a"
                        + " where lower(a.username) = :username";

        Query query = entityManager.createQuery(sql);
        query.setParameter("username", username);
        List<UserEntity> result = query.getResultList();
        if(result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public UserEntity getByEmail(String email) {
        String sql =
                "select a"
                        + " from UserEntity a"
                        + " where a.email = :email";

        Query query = entityManager.createQuery(sql);
        query.setParameter("email", email);
        List<UserEntity> result = query.getResultList();
        if(result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
