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

    private static Logger logger = Logger.getLogger(BandBOIml.class);

    @Autowired
    private BandDAO bandDAO;

    @Override
    @Transactional
    public BandBean getBand(Long id) {
        logger.info("getBand start for id: " + id);
        BandDTO bandDTO = bandDAO.findById(id);
        BandBean bean = new BandBean();
        if (bandDTO != null){
            if (bandDTO.getId() != null){
                bean.setId(bandDTO.getId());
            }
            if (bandDTO.getName() != null){
                bean.setName(bandDTO.getName());
            }
            if (bandDTO.getImage() != null){
                bean.setImage(bandDTO.getImage());
            }
            if (bandDTO.getOriginalArticle() != null){
                bean.setOriginalArticle(bandDTO.getOriginalArticle());
            }
            if (bandDTO.getFirstParagraph() != null){
                bean.setFirstParagraph(bandDTO.getFirstParagraph());
            }
            if (bandDTO.getSecondParagraph() != null){
                bean.setSecondParagraph(bandDTO.getSecondParagraph());
            }
            if (bandDTO.getThirdParagraph() != null){
                bean.setThirdParagraph(bandDTO.getThirdParagraph());
            }
            if (bandDTO.getFirstVideo() != null){
                bean.setFirstVideo(bandDTO.getFirstVideo());
            }
            if (bandDTO.getSecondVideo() != null){
                bean.setSecondVideo(bandDTO.getSecondVideo());
            }
            if (bandDTO.getThirdVideo() != null){
                bean.setThirdVideo(bandDTO.getThirdVideo());
            }
            if (bandDTO.getFourthVideo() != null){
                bean.setFourthVideo(bandDTO.getFourthVideo());
            }
        }
        logger.info("getBand end for id: " + id);
        return bean;
    }
}
