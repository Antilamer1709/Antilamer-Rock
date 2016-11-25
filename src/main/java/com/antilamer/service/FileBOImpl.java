package com.antilamer.service;

import com.antilamer.controller.FileController;
import com.antilamer.dao.BandDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.BandDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service(value = "fileBOImpl")
public class FileBOImpl implements FileBO {

    private static Logger logger = Logger.getLogger(FileBOImpl.class);

    @Value("${images.directory}")
    private String imagesDirectory;


    @Autowired
    private BandDAO bandDAO;

    @Override
    @Transactional
    public ResponseEntity<?> uploadImage(MultipartFile file, Long bandId) {
        logger.info("*** uploadImage() for bandId:" + bandId);
        try {
            validateAndPersistBand(bandId);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imagesDirectory + bandId)));
            stream.write(file.getBytes());
            stream.close();
            logger.info("*** uploadImage() end for bandId:" + bandId);
        }
        catch (Exception e) {
            logger.info("*** uploadImage() error");
            logger.info(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateAndPersistBand(Long bandId){
        BandDTO bandDTO = bandDAO.findById(bandId);
        if (bandDTO != null) {
            bandDTO.setUploadedImage(true);
            bandDAO.persist(bandDTO);
        } else {
            throw new ValidationExeption("Band with this id doesn't exist! bandId:" + bandId);
        }
    }

}
