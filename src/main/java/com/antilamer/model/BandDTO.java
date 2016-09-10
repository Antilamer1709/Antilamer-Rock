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

    @Column(name = "ORIGINAL_ARTICLE")
    private String originalArticle;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FIRST_PARAGRAPH")
    private String firstParagraph;

    @Column(name = "SECOND_PARAGRAPH")
    private String secondParagraph;

    @Column(name = "THIRD_PARAGRAPH")
    private String thirdParagraph;

    @Column(name = "FIRST_VIDEO")
    private String firstVideo;

    @Column(name = "SECOND_VIDEO")
    private String secondVideo;

    @Column(name = "THIRD_VIDEO")
    private String thirdVideo;

    @Column(name = "FOURTH_VIDEO")
    private String fourthVideo;

    public BandDTO() {
    }

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
