package com.antilamer.dao;

import com.antilamer.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RoleDAOImpl extends AbstractJpaDAOImpl<RoleEntity> implements RoleDAO{
}
