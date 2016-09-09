package com.antilamer.service;


import com.antilamer.beans.BandBean;
import com.antilamer.controller.BandController;
import com.antilamer.dao.BandDAO;
import com.antilamer.model.BandDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "bandBO")
public class BandBOIml implements BandBO {

    private static Logger logger = Logger.getLogger(BandController.class);

    @Autowired
    private BandDAO bandDAO;

    @Override
    @Transactional
    public BandBean getBand(Long id) {
        logger.info("getBand start for id: " + id);
        BandDTO bandDTO = bandDAO.findById(1l);
        BandBean bean = new BandBean();
        if (bandDTO != null){
            if (bandDTO.getId() != null){
                bean.setId(bandDTO.getId());
            }
            if (bandDTO.getName() != null){
                bean.setName(bandDTO.getName());
            }
            if (bandDTO.getContent() != null){
                bean.setContent(bandDTO.getContent());
            }
        }
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println("                   a                        ");
        System.out.println(bandDTO);
        System.out.println(bandDTO.getId());
        System.out.println(bandDTO.getName());
        System.out.println(bandDTO.getContent());
        System.out.println(bandDTO.getId());
        logger.info("getBand end for id: " + id);
        return bean;
    }
}
