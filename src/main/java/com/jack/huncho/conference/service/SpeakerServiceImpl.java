package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Override
    public List<Speaker> getSpeakers() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker getOneSpeaker(long id) {
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if(optionalSpeaker.isPresent())
            return optionalSpeaker.get();
        else
            // TODO: make a dedicated Exception class
            throw new RuntimeException("Speaker Not Found");
    }

    @Override
    public Speaker updateSpeaker(long id, Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

    @Override
    public Speaker createSpeaker(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @Override
    public void deleteSpeaker(long id) {
        speakerRepository.deleteById(id);
    }
}
