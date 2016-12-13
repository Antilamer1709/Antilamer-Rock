package com.antilamer.dao;

import com.antilamer.dto.band.BandSearchDTO;
import com.antilamer.entity.BandVersionEntity;

import java.util.List;

public interface BandVersionDAO extends AbstractJpaDAO<BandVersionEntity>{

    List<BandVersionEntity> findAllById(BandSearchDTO searhDTO);

}
