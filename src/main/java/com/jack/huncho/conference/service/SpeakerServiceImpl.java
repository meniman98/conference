package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Override
    public List<Speaker> getSpeakers() {
        return (List<Speaker>) speakerRepository.findAll();
    }
}
