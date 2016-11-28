package com.antilamer.dao;

import com.antilamer.beans.band.BandSearchBean;
import com.antilamer.model.BandVersionDTO;

import java.util.List;

public interface BandVersionDAO extends AbstractJpaDAO<BandVersionDTO>{

    List<BandVersionDTO> findAllById(BandSearchBean searhBean);

}
