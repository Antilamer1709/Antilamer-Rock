package com.antilamer.beans;

import java.io.Serializable;

public class BandBean implements Serializable {
    private Long id;
    private String name;
    private String originalArticle;
    private String image;
    private String firstParagraph;
    private String secondParagraph;
    private String thirdParagraph;
    private String firstVideo;
    private String secondVideo;
    private String thirdVideo;
    private String fourthVideo;

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

    public String getFirstParagraph() {
        return firstParagraph;
    }

    public void setFirstParagraph(String firstParagraph) {
        this.firstParagraph = firstParagraph;
    }

    public String getSecondParagraph() {
        return secondParagraph;
    }

    public void setSecondParagraph(String secondParagraph) {
        this.secondParagraph = secondParagraph;
    }

    public String getThirdParagraph() {
        return thirdParagraph;
    }

    public void setThirdParagraph(String thirdParagraph) {
        this.thirdParagraph = thirdParagraph;
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
}
