package com.antilamer.controller;

import com.antilamer.service.FileBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileController {

    private static Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    private FileBO fileBO;


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file, @RequestParam String bandId) {
        logger.info("*** uploadImage()");
        return fileBO.uploadImage(file, Long.parseLong(bandId));
    }

}
