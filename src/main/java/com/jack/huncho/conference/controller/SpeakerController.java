package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.repository.SpeakerRepository;
import com.jack.huncho.conference.service.SpeakerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpeakerController {

    @Autowired
    SpeakerServiceImpl speakerService;

    @GetMapping("/speaker/get")
    Iterable<Speaker> getAllSpeakers() {
       return speakerService.getSpeakers();
    }

    @GetMapping("/speaker/get/{id}")
    Speaker getOneSpeaker(@PathVariable long id) {
       return speakerService.getOneSpeaker(id);
    }

    // TODO: put returns a 500
    @PutMapping("/speaker/put/{id}")
    Speaker updateSpeaker(@RequestBody Speaker speaker, @PathVariable long id) {
        return speakerService.updateSpeaker(id, speaker);
    }

    @PostMapping("/speaker/post")
    Speaker createSpeaker(@RequestBody Speaker speaker) {
        return speakerService.createSpeaker(speaker);
    }

    // TODO: delete returns a 500
    @DeleteMapping("/speaker/delete/{id}")
    void deleteSpeaker(@PathVariable long id) {
        speakerService.deleteSpeaker(id);
    }
}
