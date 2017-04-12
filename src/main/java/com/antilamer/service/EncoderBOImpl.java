package com.antilamer.service;

import com.antilamer.dto.encoder.EncoderDTO;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Service;


@Service(value = "encoderBO")
public class EncoderBOImpl implements EncoderBO{

    private static Logger logger = Logger.getLogger(EncoderBOImpl.class);

    @Override
    public EncoderDTO convert(EncoderDTO encoderDTO) {
        logger.info("*** getting cryptoUtils and setting password");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encoderDTO.getPassword());

        logger.info("*** start convert string: " + encoderDTO.getStringToEncode());
        String result;
        try {
            result = decode(encryptor, encoderDTO.getStringToEncode());
            logger.info("*** string is decrypted: " + result);
        }
        catch (EncryptionOperationNotPossibleException e){
            result = encode(encryptor, encoderDTO.getStringToEncode());
            logger.info("*** string is encrypted " + result);
        }
        encoderDTO.setEncodedString(result);

        return encoderDTO;
    }

    private String encode(StandardPBEStringEncryptor encryptor, String value) {
        return encryptor.encrypt(value);
    }

    private String decode(StandardPBEStringEncryptor encryptor, String value) {
        return encryptor.decrypt(value);
    }

}
