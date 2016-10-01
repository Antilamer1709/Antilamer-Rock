package com.antilamer.dao;

import com.antilamer.model.RoleDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RoleDAOImpl extends AbstractJpaDAOImpl<RoleDTO> implements RoleDAO{
}
