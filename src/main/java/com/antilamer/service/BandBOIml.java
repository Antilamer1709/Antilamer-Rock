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

    @Override
    @Transactional
    public void saveBand(BandBean bean) {
        logger.info("saveBand start for id: " + bean.getId());
        BandDTO bandDTO = bandDAO.findById(bean.getId());
        if (bean.getName() != null){
            bandDTO.setName(bean.getName());
        }
        if (bean.getImage() != null){
            bandDTO.setImage(bean.getImage());
        }
        if (bean.getOriginalArticle() != null){
            bandDTO.setOriginalArticle(bean.getOriginalArticle());
        }
        if (bean.getFirstParagraph() != null){
            bandDTO.setFirstParagraph(bean.getFirstParagraph());
        }
        if (bean.getSecondParagraph() != null){
            bandDTO.setSecondParagraph(bean.getSecondParagraph());
        }
        if (bean.getThirdParagraph() != null){
            bandDTO.setThirdParagraph(bean.getThirdParagraph());
        }
        if (bean.getFirstVideo() != null){
            bandDTO.setFirstVideo(bean.getFirstVideo());
        }
        if (bean.getSecondVideo() != null){
            bandDTO.setSecondVideo(bean.getSecondVideo());
        }
        if (bean.getThirdVideo() != null){
            bandDTO.setThirdVideo(bean.getThirdVideo());
        }
        if (bean.getFourthVideo() != null){
            bandDTO.setFourthVideo(bean.getFourthVideo());
        }
        bandDAO.persist(bandDTO);
        logger.info("saveBand end for id: " + bean.getId());
    }
}
