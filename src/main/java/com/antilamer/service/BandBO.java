package com.antilamer.service;


import com.antilamer.beans.BandBean;
import com.antilamer.beans.BandHistoryBean;
import com.antilamer.beans.BandSearhBean;

import java.util.List;

public interface BandBO {

    BandBean getBand(Long id);

    void saveBand(BandBean bean);

    List<BandHistoryBean> seachBandHistory(BandSearhBean searhBean);
}
