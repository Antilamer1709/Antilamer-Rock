package com.antilamer.beans.common;

import java.io.Serializable;


public class CommonSearchBean implements Serializable {

    private Long id;
    private Long versionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }
}
