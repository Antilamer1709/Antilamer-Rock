package com.antilamer.service;


import com.antilamer.dto.band.BandDTO;
import com.antilamer.dto.band.BandHistoryDTO;
import com.antilamer.dto.band.BandSearchDTO;
import com.antilamer.dto.common.CommonSearchDTO;

import java.util.List;

public interface BandBO {

    BandDTO getBand(Long id);

    BandDTO getBandVersion(CommonSearchDTO searchDTO);

    void saveBand(BandDTO bandDTO);

    void makeVersionCurrent(CommonSearchDTO searchDTO);

    List<BandHistoryDTO> seachBandHistory(BandSearchDTO searhDTO);
}
