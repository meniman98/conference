package com.jack.huncho.conference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalTime start;
    private LocalTime end;

    @ManyToMany()
    @JsonIgnore
    private List<Speaker> speakers;
    private int length;
    private String description;
}
