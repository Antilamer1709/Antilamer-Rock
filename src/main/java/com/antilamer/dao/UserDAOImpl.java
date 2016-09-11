package com.antilamer.dao;


import com.antilamer.model.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class UserDAOImpl extends AbstractJpaDAOImpl<UserDTO> implements UserDAO{
    @Override
    public UserDTO getByUsername(String username) {
        String sql =
                "select a"
                        + " from UserDTO a"
                        + " where a.username = :username";

        Query query = entityManager.createQuery(sql);
        query.setParameter("username", username);
        List<UserDTO> result = query.getResultList();
        if(result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public UserDTO getByEmail(String email) {
        String sql =
                "select a"
                        + " from UserDTO a"
                        + " where a.email = :email";

        Query query = entityManager.createQuery(sql);
        query.setParameter("email", email);
        List<UserDTO> result = query.getResultList();
        if(result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
