package com.antilamer.beans;

import com.antilamer.model.BandVersionDTO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BandHistoryBean implements Serializable {
    private Long id;
    private String user;
    private String creationDate;

    public BandHistoryBean() {
    }

    public BandHistoryBean(BandVersionDTO versionDTO) {
        this.id = versionDTO.getId();
        this.user = versionDTO.getUser().getUsername();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        this.creationDate = format1.format(versionDTO.getCreationDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
