package com.jack.huncho.conference.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

// this json ignore line saved my error
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;
    private String name;

    @Max(value = 5)
    private int rating;
    private String reviewType;

    public Review(String description, String name, int rating, String reviewType) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.reviewType = reviewType;
    }

    public Review() {}

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public static void main(String[] args) {
        Review review = new Review();
        review.setReviewType("speaker");
        System.out.println(review.getReviewType());

    }
}
