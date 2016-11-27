package com.antilamer.dao;

import com.antilamer.model.BandVersionDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BandVersionDAOImpl extends AbstractJpaDAOImpl<BandVersionDTO> implements BandVersionDAO {
}
