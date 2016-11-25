package com.antilamer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BANDS")
public class BandDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

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

    public BandDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(Boolean uploadedImage) {
        this.uploadedImage = uploadedImage;
    }
}
