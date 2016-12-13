package com.antilamer.dto.band;

import com.antilamer.entity.BandVersionEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class BandHistoryDTO implements Serializable {
    private Long id;
    private String user;
    private String creationDate;

    public BandHistoryDTO() {
    }

    public BandHistoryDTO(BandVersionEntity versionDTO) {
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
