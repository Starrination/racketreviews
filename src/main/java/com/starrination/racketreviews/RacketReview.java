package com.starrination.racketreviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RacketReview {
    
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer id;

    private String reviewerName;
    private String racketName;
    private Integer rating;
    private String reviewText;
    private Integer racketId;
    private Float racketAvgRating;
    
    public RacketReview() {
       
    }
    public RacketReview(String reviewerName, Integer racketId, String racketName, Integer rating, String reviewText) {
        this.reviewerName = reviewerName;
        this.racketId = racketId;
        this.racketName = racketName;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Integer getId() {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String name) {
        this.reviewerName = name;
    }

    public String getRacketName() {
        return racketName;
    }

    public void setRacketName(String racketName) {
        this.racketName = racketName;
    }

    public Integer getRacketId() {
        return racketId;
    }

    public void setRacketId(Integer racketId) {
        this.racketId = racketId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Float getRacketAvgRating() {
        return racketAvgRating;
    }

    public void setRacketAvgRating(Float racketAvgRating) {
        this.racketAvgRating = racketAvgRating;
    }
}
