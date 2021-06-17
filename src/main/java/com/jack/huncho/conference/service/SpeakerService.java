package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> getSpeakers();
    Speaker getOneSpeaker(long id);
    Speaker updateSpeaker(long id, Speaker speaker);
    Speaker createSpeaker(Speaker speaker);
    void deleteSpeaker(long id);
}
