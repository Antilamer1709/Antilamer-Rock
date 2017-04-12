package com.antilamer.controller;

import com.antilamer.dto.encoder.EncoderDTO;
import com.antilamer.service.EncoderBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/encoder")
public class EncoderController {

    private static Logger logger = Logger.getLogger(EncoderController.class);

    @Autowired
    private EncoderBO encoderBO;

    @RequestMapping(value = "encode", method = RequestMethod.POST)
    public
    @ResponseBody
    EncoderDTO encode(@RequestBody EncoderDTO encoderDTO) throws Exception {
        logger.info("*** encode()");
        return encoderBO.convert(encoderDTO);
    }
}
