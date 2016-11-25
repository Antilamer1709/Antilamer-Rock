package com.antilamer.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileBO {
    ResponseEntity<?> uploadImage(MultipartFile file, Long bandId);
}
