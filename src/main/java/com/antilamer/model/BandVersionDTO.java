package com.antilamer.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "BAND_VERSION")
public class BandVersionDTO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CURRENT_VERSION")
    private Boolean currentVersion;

    @Column(name = "BAND_CONTENT")
    private String bandContent;

    @Column(name = "ORIGINAL_ARTICLE")
    private String originalArticle;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FIRST_VIDEO")
    private String firstVideo;

    @Column(name = "SECOND_VIDEO")
    private String secondVideo;

    @Column(name = "THIRD_VIDEO")
    private String thirdVideo;

    @Column(name = "FOURTH_VIDEO")
    private String fourthVideo;

    @Column(name = "UPLOADED_IMAGE")
    private Boolean uploadedImage;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAND_ID")
    @Index(name = "BV_BAND_ID_IDX")
    private BandDTO band;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Index(name = "BV_USER_ID_IDX")
    private UserDTO user;

    public BandVersionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Boolean currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getBandContent() {
        return bandContent;
    }

    public void setBandContent(String bandContent) {
        this.bandContent = bandContent;
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

    public Boolean getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(Boolean uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public BandDTO getBand() {
        return band;
    }

    public void setBand(BandDTO band) {
        this.band = band;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
