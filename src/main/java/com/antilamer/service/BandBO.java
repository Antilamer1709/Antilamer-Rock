package com.antilamer.service;


import com.antilamer.beans.band.BandBean;
import com.antilamer.beans.band.BandHistoryBean;
import com.antilamer.beans.band.BandSearchBean;
import com.antilamer.beans.common.CommonSearchBean;

import java.util.List;

public interface BandBO {

    BandBean getBand(Long id);

    BandBean getBandVersion(CommonSearchBean searchBean);

    void saveBand(BandBean bean);

    void makeVersionCurrent(CommonSearchBean searchBean);

    List<BandHistoryBean> seachBandHistory(BandSearchBean searhBean);
}
