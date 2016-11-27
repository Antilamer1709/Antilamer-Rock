package com.antilamer.beans;

import com.antilamer.controller.BandController;
import com.antilamer.model.BandVersionDTO;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

public class BandHistoryBean implements Serializable {
    private Long id;
    private String user;
    private Date creationDate;

    public BandHistoryBean() {
    }

    public BandHistoryBean(BandVersionDTO versionDTO) {
        this.id = versionDTO.getId();
        this.user = versionDTO.getUser().getUsername();
        this.creationDate = versionDTO.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
