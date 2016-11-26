package com.antilamer.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileBO {

    ResponseEntity<?> uploadImage(MultipartFile file, Long bandId);

    ResponseEntity<byte[]> getBandImage(String bandId) throws IOException;
}
