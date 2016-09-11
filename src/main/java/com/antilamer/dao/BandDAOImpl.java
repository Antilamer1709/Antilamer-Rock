package com.antilamer.dao;

import com.antilamer.model.BandDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BandDAOImpl extends AbstractJpaDAOImpl<BandDTO> implements BandDAO {

}
