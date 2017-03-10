package com.antilamer.controller;

import com.antilamer.dto.band.BandDTO;
import com.antilamer.dto.band.BandHistoryDTO;
import com.antilamer.dto.band.BandSearchDTO;
import com.antilamer.dto.common.CommonSearchDTO;
import com.antilamer.exeptions.ServerExeption;
import com.antilamer.service.BandBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BandController {

    private static Logger logger = Logger.getLogger(BandController.class);

    @Autowired
    private BandBO bandBO;


    @RequestMapping(value = "/getBand", method = RequestMethod.POST)
    public
    @ResponseBody
    BandDTO getBand(@RequestBody CommonSearchDTO searchDTO) {
        logger.info("getBand()");
        if (searchDTO != null && searchDTO.getId() != null) {
            return bandBO.getBand(searchDTO.getId());
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "/getBandVersion", method = RequestMethod.POST)
    public
    @ResponseBody
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    BandDTO getBandVersion(@RequestBody CommonSearchDTO searchDTO) {
        if (searchDTO != null && searchDTO.getId() != null) {
            logger.info("getBandVersion() bandId: " + searchDTO.getId());
            return bandBO.getBandVersion(searchDTO);
        }
        throw new ServerExeption("Server internal error, please contact to developer");
    }

    @RequestMapping(value = "saveBand", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @ResponseStatus(value = HttpStatus.OK)
    public void saveBand(@RequestBody BandDTO searchDTO){
        logger.info("*** saveBand()");
        if (searchDTO != null) {
            bandBO.saveBand(searchDTO);
            return;
        }
        throw new ServerExeption("Server internal error, please contact to developer. Band is not saved!");
    }

    @RequestMapping(value = "makeVersionCurrent", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @ResponseStatus(value = HttpStatus.OK)
    public void makeVersionCurrent(@RequestBody CommonSearchDTO searchDTO){
        if (searchDTO != null) {
            logger.info("*** makeVersionCurrent() for bandId: " + searchDTO.getId());
            bandBO.makeVersionCurrent(searchDTO);
            return;
        }
        throw new ServerExeption("Server internal error, please contact to developer. Band is not saved!");
    }

    @RequestMapping(value = "seachBandHistory", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    public
    @ResponseBody
    List<BandHistoryDTO> seachBandHistory(HttpServletRequest req, @RequestBody BandSearchDTO searchDTO){
        logger.info("*** seachBandHistory() ");
        if (searchDTO != null){
            return bandBO.seachBandHistory(searchDTO);
        }
        return null;
    }
}