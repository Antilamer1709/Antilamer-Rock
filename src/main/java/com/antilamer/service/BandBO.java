package com.antilamer.service;


import com.antilamer.beans.BandBean;
import com.antilamer.beans.BandHistoryBean;
import com.antilamer.beans.BandSearchBean;
import com.antilamer.beans.CommonSearchBean;

import java.util.List;

public interface BandBO {

    BandBean getBand(Long id);

    BandBean getBandVersion(CommonSearchBean searchBean);

    void saveBand(BandBean bean);

    void makeVersionCurrent(CommonSearchBean searchBean);

    List<BandHistoryBean> seachBandHistory(BandSearchBean searhBean);
}
