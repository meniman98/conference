package com.jack.huncho.conference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @ElementCollection
    private List<String> skills;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;


    @OneToMany(cascade=CascadeType.ALL)
    private List<Review> reviews;

    private String title;
    private String company;
    private String bio;

    public Speaker(String name) {
        this.name = name;
    }

    public Speaker(String name, List<String> skills, List<Review> reviews) {
        this.name = name;
        this.skills = skills;
        this.reviews = reviews;
    }

    public Speaker(String name, List<String> skills, String title, String company, String bio) {
        this.name = name;
        this.skills = skills;
        this.title = title;
        this.company = company;
        this.bio = bio;
    }



    public Speaker() { }

    public Speaker(long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Review review) {
        this.reviews.add(review);
    }
}
