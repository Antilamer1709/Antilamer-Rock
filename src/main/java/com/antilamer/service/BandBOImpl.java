package com.antilamer.service;


import com.antilamer.beans.BandBean;
import com.antilamer.beans.BandHistoryBean;
import com.antilamer.beans.BandSearchBean;
import com.antilamer.beans.CommonSearchBean;
import com.antilamer.dao.BandDAO;
import com.antilamer.dao.BandVersionDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.BandDTO;
import com.antilamer.model.BandVersionDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "bandBO")
public class BandBOImpl implements BandBO {

    private static Logger logger = Logger.getLogger(BandBOImpl.class);

    @Autowired
    private BandDAO bandDAO;

    @Autowired
    private BandVersionDAO bandVersionDAO;

    @Autowired
    private UserBO userBO;

    @Value("${files.image.link.prefix}")
    private String linkPrefix;

    @Override
    @Transactional
    public BandBean getBand(Long id) {
        logger.info("getBand start for id: " + id);
        BandDTO bandDTO = bandDAO.findById(id);
        BandBean bean = new BandBean();
        if (bandDTO != null) {
            if (bandDTO.getId() != null) {
                bean.setId(bandDTO.getId());
            }
            if (bandDTO.getCurrentVersion().getBandContent() != null) {
                bean.setBandContent(bandDTO.getCurrentVersion().getBandContent());
            }
            if (bandDTO.getCurrentVersion().getImage() != null) {
                bean.setImage(bandDTO.getCurrentVersion().getImage());
            }
            if (bandDTO.getName() != null) {
                bean.setName(bandDTO.getName());
            }
            if (bandDTO.getCurrentVersion().getOriginalArticle() != null) {
                bean.setOriginalArticle(bandDTO.getCurrentVersion().getOriginalArticle());
            }
            if (bandDTO.getCurrentVersion().getFirstVideo() != null) {
                bean.setFirstVideo(bandDTO.getCurrentVersion().getFirstVideo());
            }
            if (bandDTO.getCurrentVersion().getSecondVideo() != null) {
                bean.setSecondVideo(bandDTO.getCurrentVersion().getSecondVideo());
            }
            if (bandDTO.getCurrentVersion().getThirdVideo() != null) {
                bean.setThirdVideo(bandDTO.getCurrentVersion().getThirdVideo());
            }
            if (bandDTO.getCurrentVersion().getFourthVideo() != null) {
                bean.setFourthVideo(bandDTO.getCurrentVersion().getFourthVideo());
            }
            if (bandDTO.getCurrentVersion().getUploadedImage() != null) {
                bean.setUploadedImage(bandDTO.getCurrentVersion().getUploadedImage());
            }
        }
        logger.info("getBand end for id: " + id);
        return bean;
    }

    @Override
    public BandBean getBandVersion(CommonSearchBean searchBean) {
        logger.info("getBandVersion() start for verionId: " + searchBean.getVersionId());
        BandDTO bandDTO = bandDAO.findById(searchBean.getId());
        BandVersionDTO bandVersionDTO = bandVersionDAO.findById(searchBean.getVersionId());
        BandBean bean = new BandBean();
        if (bandVersionDTO != null && bandDTO != null) {
            if (bandVersionDTO.getId() != null) {
                bean.setId(bandVersionDTO.getId());
            }
            if (bandVersionDTO.getBandContent() != null) {
                bean.setBandContent(bandVersionDTO.getBandContent());
            }
            if (bandVersionDTO.getImage() != null) {
                bean.setImage(bandVersionDTO.getImage());
            }
            if (bandDTO.getName() != null) {
                bean.setName(bandDTO.getName());
            }
            if (bandVersionDTO.getOriginalArticle() != null) {
                bean.setOriginalArticle(bandVersionDTO.getOriginalArticle());
            }
            if (bandVersionDTO.getFirstVideo() != null) {
                bean.setFirstVideo(bandVersionDTO.getFirstVideo());
            }
            if (bandVersionDTO.getSecondVideo() != null) {
                bean.setSecondVideo(bandVersionDTO.getSecondVideo());
            }
            if (bandVersionDTO.getThirdVideo() != null) {
                bean.setThirdVideo(bandVersionDTO.getThirdVideo());
            }
            if (bandVersionDTO.getFourthVideo() != null) {
                bean.setFourthVideo(bandVersionDTO.getFourthVideo());
            }
            if (bandVersionDTO.getUploadedImage() != null) {
                bean.setUploadedImage(bandVersionDTO.getUploadedImage());
            }
        }
        return bean;
    }

    @Override
    @Transactional
    public void saveBand(BandBean bean) {
        logger.info("saveBand start for id: " + bean.getId());
        BandDTO bandDTO = bandDAO.findById(bean.getId());
        if (bean.getBandContent().isEmpty()) {
            throw new ValidationExeption("Content can't be empty!");
        }
        if (bandDTO != null) {
            BandVersionDTO versionDTO = new BandVersionDTO();
            if (bean.getOriginalArticle() != null) {
                versionDTO.setOriginalArticle(bean.getOriginalArticle());
            }
            if (bean.getBandContent() != null) {
                versionDTO.setBandContent(bean.getBandContent());
            }
            if (bean.getFirstVideo() != null) {
                versionDTO.setFirstVideo(bean.getFirstVideo());
            }
            if (bean.getSecondVideo() != null) {
                versionDTO.setSecondVideo(bean.getSecondVideo());
            }
            if (bean.getThirdVideo() != null) {
                versionDTO.setThirdVideo(bean.getThirdVideo());
            }
            if (bean.getFourthVideo() != null) {
                versionDTO.setFourthVideo(bean.getFourthVideo());
            }
            if (bean.getFourthVideo() != null) {
                versionDTO.setFourthVideo(bean.getFourthVideo());
            }
            if (bean.getUploadedImage() != null) {
                if (bean.getUploadedImage()) {
                    versionDTO.setUploadedImage(true);
                    versionDTO.setImage(linkPrefix + bandDTO.getId());
                } else {
                    versionDTO.setUploadedImage(false);
                    versionDTO.setImage(bean.getImage());
                }
            }
            versionDTO.setCurrentVersion(true);
            versionDTO.setCreationDate(new Date());
            versionDTO.setBand(bandDTO);
            versionDTO.setUser(userBO.getLoggedUser());
            bandVersionDAO.persist(versionDTO);
            bandDTO.setCurrentVersion(versionDTO);
            bandDAO.persist(bandDTO);
            logger.info("saveBand end for id: " + bean.getId());
        } else {
            throw new ValidationExeption("Band doesn't exist with id " + bean.getId());
        }
    }

    @Override
    @Transactional
    public void makeVersionCurrent(CommonSearchBean searchBean) {
        logger.info("makeVersionCurrent start for versionId: " + searchBean.getVersionId());
        BandDTO bandDTO = bandDAO.findById(searchBean.getId());
        BandVersionDTO versionDTO = bandVersionDAO.findById(searchBean.getVersionId());
        if (bandDTO != null && versionDTO != null) {
            versionDTO.setCurrentVersion(true);
            versionDTO.setCreationDate(new Date());
            versionDTO.setBand(bandDTO);
            versionDTO.setUser(userBO.getLoggedUser());
            bandDTO.setCurrentVersion(versionDTO);
            bandDAO.persist(bandDTO);
            logger.info("makeVersionCurrent end for id: " + searchBean.getVersionId());
        } else {
            throw new ValidationExeption("Band doesn't exist with id " + searchBean.getId());
        }
    }

    @Override
    @Transactional
    public List<BandHistoryBean> seachBandHistory(BandSearchBean searchBean) {
        logger.info("seachBandHistory start for id: " + searchBean.getId());
        BandDTO bandDTO = bandDAO.findById(searchBean.getId());
        if (bandDTO != null) {
            List<BandVersionDTO> versionDTOs = bandVersionDAO.findAllById(searchBean);
            List<BandHistoryBean> beanList = new ArrayList<>();
            if (versionDTOs != null) {
                for (BandVersionDTO versionDTO : versionDTOs) {
                    beanList.add(new BandHistoryBean(versionDTO));
                }
            }
            return beanList;
        }
        throw new ValidationExeption("Band doesn't exist with id " + searchBean.getId());
    }
}
