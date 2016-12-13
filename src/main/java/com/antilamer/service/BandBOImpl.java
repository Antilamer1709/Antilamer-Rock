package com.antilamer.service;


import com.antilamer.dto.band.BandDTO;
import com.antilamer.dto.band.BandHistoryDTO;
import com.antilamer.dto.band.BandSearchDTO;
import com.antilamer.dto.common.CommonSearchDTO;
import com.antilamer.dao.BandDAO;
import com.antilamer.dao.BandVersionDAO;
import com.antilamer.entity.BandEntity;
import com.antilamer.entity.BandVersionEntity;
import com.antilamer.exeptions.ValidationExeption;
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
    public BandDTO getBand(Long id) {
        logger.info("getBand start for id: " + id);
        BandEntity bandEntity = bandDAO.findById(id);
        BandDTO bandDTO = new BandDTO();
        if (bandEntity != null) {
            if (bandEntity.getId() != null) {
                bandDTO.setId(bandEntity.getId());
            }
            if (bandEntity.getName() != null) {
                bandDTO.setName(bandEntity.getName());
            }
            initBandDTO(bandEntity.getCurrentVersion(), bandDTO);
        }
        logger.info("getBand end for id: " + id);
        return bandDTO;
    }

    @Override
    public BandDTO getBandVersion(CommonSearchDTO searchDTO) {
        logger.info("getBandVersion() start for verionId: " + searchDTO.getVersionId());
        BandEntity bandEntity = bandDAO.findById(searchDTO.getId());
        BandVersionEntity bandVersionEntity = bandVersionDAO.findById(searchDTO.getVersionId());
        BandDTO bandDTO = new BandDTO();
        if (bandVersionEntity != null && bandEntity != null) {
            if (bandVersionEntity.getId() != null) {
                bandDTO.setId(bandVersionEntity.getId());
            }
            if (bandEntity.getName() != null) {
                bandDTO.setName(bandEntity.getName());
            }
            initBandDTO(bandVersionEntity, bandDTO);
        }
        return bandDTO;
    }

    @Override
    @Transactional
    public void saveBand(BandDTO bandDTO) {
        logger.info("saveBand start for id: " + bandDTO.getId());
        BandEntity bandEntity = bandDAO.findById(bandDTO.getId());
        if (bandDTO.getBandContent().isEmpty()) {
            throw new ValidationExeption("Content can't be empty!");
        }
        if (bandEntity != null) {
            setOldVersion(bandEntity);
            BandVersionEntity versionEntity = createAndInitBandVersionEntity(bandDTO, bandEntity);
            bandVersionDAO.persist(versionEntity);
            bandEntity.setCurrentVersion(versionEntity);
            bandDAO.persist(bandEntity);
            logger.info("saveBand end for id: " + bandDTO.getId());
        } else {
            throw new ValidationExeption("Band doesn't exist with id " + bandDTO.getId());
        }
    }

    @Override
    @Transactional
    public void makeVersionCurrent(CommonSearchDTO searchDTO) {
        logger.info("makeVersionCurrent start for versionId: " + searchDTO.getVersionId());
        BandEntity bandEntity = bandDAO.findById(searchDTO.getId());
        BandVersionEntity versionEntity = bandVersionDAO.findById(searchDTO.getVersionId());
        if (bandEntity != null && versionEntity != null) {
            setOldVersion(bandEntity);
            versionEntity.setCurrentVersion(true);
            versionEntity.setCreationDate(new Date());
            versionEntity.setBand(bandEntity);
            versionEntity.setUser(userBO.getLoggedUser());
            bandEntity.setCurrentVersion(versionEntity);
            bandDAO.persist(bandEntity);
            logger.info("makeVersionCurrent end for id: " + searchDTO.getVersionId());
        } else {
            throw new ValidationExeption("Band doesn't exist with id " + searchDTO.getId());
        }
    }

    @Override
    @Transactional
    public List<BandHistoryDTO> seachBandHistory(BandSearchDTO searchDTO) {
        logger.info("seachBandHistory start for id: " + searchDTO.getId());
        BandEntity bandEntity = bandDAO.findById(searchDTO.getId());
        if (bandEntity != null) {
            List<BandVersionEntity> versionEntities = bandVersionDAO.findAllById(searchDTO);
            List<BandHistoryDTO> historyDTOs = new ArrayList<>();
            if (versionEntities != null) {
                for (BandVersionEntity versionEntity : versionEntities) {
                    historyDTOs.add(new BandHistoryDTO(versionEntity));
                }
            }
            return historyDTOs;
        }
        throw new ValidationExeption("Band doesn't exist with id " + searchDTO.getId());
    }

    private void initBandDTO(BandVersionEntity versionEntity, BandDTO bandDTO){
        if (versionEntity.getBandContent() != null) {
            bandDTO.setBandContent(versionEntity.getBandContent());
        }
        if (versionEntity.getImage() != null) {
            bandDTO.setImage(versionEntity.getImage());
        }
        if (versionEntity.getOriginalArticle() != null) {
            bandDTO.setOriginalArticle(versionEntity.getOriginalArticle());
        }
        if (versionEntity.getFirstVideo() != null) {
            bandDTO.setFirstVideo(versionEntity.getFirstVideo());
        }
        if (versionEntity.getSecondVideo() != null) {
            bandDTO.setSecondVideo(versionEntity.getSecondVideo());
        }
        if (versionEntity.getThirdVideo() != null) {
            bandDTO.setThirdVideo(versionEntity.getThirdVideo());
        }
        if (versionEntity.getFourthVideo() != null) {
            bandDTO.setFourthVideo(versionEntity.getFourthVideo());
        }
        if (versionEntity.getUploadedImage() != null) {
            bandDTO.setUploadedImage(versionEntity.getUploadedImage());
        }
        if (versionEntity.getCurrentVersion() != null) {
            bandDTO.setCurrentVersion(versionEntity.getCurrentVersion());
        }
    }

    private BandVersionEntity createAndInitBandVersionEntity(BandDTO bandDTO, BandEntity bandEntity){
        BandVersionEntity versionEntity = new BandVersionEntity();
        if (bandDTO.getOriginalArticle() != null) {
            versionEntity.setOriginalArticle(bandDTO.getOriginalArticle());
        }
        if (bandDTO.getBandContent() != null) {
            versionEntity.setBandContent(bandDTO.getBandContent());
        }
        if (bandDTO.getFirstVideo() != null) {
            versionEntity.setFirstVideo(bandDTO.getFirstVideo());
        }
        if (bandDTO.getSecondVideo() != null) {
            versionEntity.setSecondVideo(bandDTO.getSecondVideo());
        }
        if (bandDTO.getThirdVideo() != null) {
            versionEntity.setThirdVideo(bandDTO.getThirdVideo());
        }
        if (bandDTO.getFourthVideo() != null) {
            versionEntity.setFourthVideo(bandDTO.getFourthVideo());
        }
        if (bandDTO.getFourthVideo() != null) {
            versionEntity.setFourthVideo(bandDTO.getFourthVideo());
        }
        if (bandDTO.getUploadedImage() != null) {
            if (bandDTO.getUploadedImage()) {
                versionEntity.setUploadedImage(true);
                versionEntity.setImage(linkPrefix + bandEntity.getId());
            } else {
                versionEntity.setUploadedImage(false);
                versionEntity.setImage(bandDTO.getImage());
            }
        }
        versionEntity.setCurrentVersion(true);
        versionEntity.setCreationDate(new Date());
        versionEntity.setBand(bandEntity);
        versionEntity.setUser(userBO.getLoggedUser());
        return versionEntity;
    }

    private void setOldVersion(BandEntity bandEntity){
        BandVersionEntity currentVersion = bandEntity.getCurrentVersion();
        currentVersion.setCurrentVersion(false);
        bandVersionDAO.persist(currentVersion);
    }
}
