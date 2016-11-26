package com.antilamer.service;


import com.antilamer.beans.BandBean;
import com.antilamer.dao.BandDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.BandDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "bandBO")
public class BandBOImpl implements BandBO {

    private static Logger logger = Logger.getLogger(BandBOImpl.class);

    @Autowired
    private BandDAO bandDAO;

    @Value("${files.image.link.prefix}")
    private String linkPrefix;

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
            if (bandDTO.getBandContent() != null){
                bean.setBandContent(bandDTO.getBandContent());
            }
            if (bandDTO.getImage() != null){
                bean.setImage(bandDTO.getImage());
            }
            if (bandDTO.getName() != null){
                bean.setName(bandDTO.getName());
            }
            if (bandDTO.getOriginalArticle() != null){
                bean.setOriginalArticle(bandDTO.getOriginalArticle());
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
            if (bandDTO.getUploadedImage() != null){
                bean.setUploadedImage(bandDTO.getUploadedImage());
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
        if (bean.getBandContent().isEmpty()){
            throw new ValidationExeption("Content can't be empty!");
        }
        if (bandDTO != null) {
            if (bean.getOriginalArticle() != null) {
                bandDTO.setOriginalArticle(bean.getOriginalArticle());
            }
            if (bean.getBandContent() != null) {
                bandDTO.setBandContent(bean.getBandContent());
            }
            if (bean.getFirstVideo() != null) {
                bandDTO.setFirstVideo(bean.getFirstVideo());
            }
            if (bean.getSecondVideo() != null) {
                bandDTO.setSecondVideo(bean.getSecondVideo());
            }
            if (bean.getThirdVideo() != null) {
                bandDTO.setThirdVideo(bean.getThirdVideo());
            }
            if (bean.getFourthVideo() != null) {
                bandDTO.setFourthVideo(bean.getFourthVideo());
            }
            if (bean.getFourthVideo() != null) {
                bandDTO.setFourthVideo(bean.getFourthVideo());
            }
            if (bean.getUploadedImage() != null) {
                if (bean.getUploadedImage()){
                    bandDTO.setUploadedImage(true);
                    bandDTO.setImage(linkPrefix + bandDTO.getId());
                } else{
                    bandDTO.setUploadedImage(false);
                    bandDTO.setImage(bean.getImage());
                }
                bandDTO.setFourthVideo(bean.getFourthVideo());
            }
            bandDAO.persist(bandDTO);
            logger.info("saveBand end for id: " + bean.getId());
        } else {
            throw new ValidationExeption("Band doesn't exist with id " + bean.getId());
        }
    }

}
