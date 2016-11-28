package com.antilamer.beans.band;

import java.io.Serializable;

public class BandBean implements Serializable {
    private Long id;
    private String name;
    private String bandContent;
    private String originalArticle;
    private String image;
    private String firstVideo;
    private String secondVideo;
    private String thirdVideo;
    private String fourthVideo;
    private Boolean uploadedImage;
    private Boolean currentVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalArticle() {
        return originalArticle;
    }

    public void setOriginalArticle(String originalArticle) {
        this.originalArticle = originalArticle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstVideo() {
        return firstVideo;
    }

    public void setFirstVideo(String firstVideo) {
        this.firstVideo = firstVideo;
    }

    public String getSecondVideo() {
        return secondVideo;
    }

    public void setSecondVideo(String secondVideo) {
        this.secondVideo = secondVideo;
    }

    public String getThirdVideo() {
        return thirdVideo;
    }

    public void setThirdVideo(String thirdVideo) {
        this.thirdVideo = thirdVideo;
    }

    public String getFourthVideo() {
        return fourthVideo;
    }

    public void setFourthVideo(String fourthVideo) {
        this.fourthVideo = fourthVideo;
    }

    public String getBandContent() {
        return bandContent;
    }

    public void setBandContent(String bandContent) {
        this.bandContent = bandContent;
    }

    public Boolean getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(Boolean uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public Boolean getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Boolean currentVersion) {
        this.currentVersion = currentVersion;
    }
}
