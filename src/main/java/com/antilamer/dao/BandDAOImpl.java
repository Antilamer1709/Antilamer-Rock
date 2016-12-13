package com.antilamer.dao;

import com.antilamer.entity.BandEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BandDAOImpl extends AbstractJpaDAOImpl<BandEntity> implements BandDAO {

}
