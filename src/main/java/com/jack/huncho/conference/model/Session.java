package com.jack.huncho.conference.model;

import java.time.LocalTime;
import java.util.List;

public class Session {
    private String name;
    private LocalTime start;
    private LocalTime end;
    private List<Speaker> speakers;
    private int length;
    private String description;
}
