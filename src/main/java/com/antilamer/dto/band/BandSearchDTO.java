package com.antilamer.dto.band;

import java.io.Serializable;

public class BandSearchDTO implements Serializable {
    private Long id;
    private Long page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }
}
