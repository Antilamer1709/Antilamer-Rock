package com.antilamer.service;

import com.antilamer.dao.BandDAO;
import com.antilamer.exeptions.ValidationExeption;
import com.antilamer.model.BandDTO;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

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
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(imagesDirectory + bandId)));
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

    @Override
    public ResponseEntity<byte[]> getBandImage(String bandId) throws IOException {
        File file = new File(imagesDirectory + bandId);
        InputStream in = new FileInputStream(file);
        final HttpHeaders headers = new HttpHeaders();
        Long fileLength = file.length();
        headers.set("Content-Type", "image/jpeg");
        headers.set("Content-Length", fileLength.toString());
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.PARTIAL_CONTENT);

    }

}
