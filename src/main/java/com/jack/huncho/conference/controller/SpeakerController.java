package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.Constants;
import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.service.SpeakerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class SpeakerController {

    @Autowired
    SpeakerServiceImpl speakerService;

    // TODO: make an exception when an empty list is returned
    @GetMapping(Constants.SPEAKER_GET)
    Iterable<Speaker> getAllSpeakers() {
        return speakerService.getSpeakers();
    }

    @GetMapping(Constants.SPEAKER_GET_ONE)
    Speaker getOneSpeaker(@PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id)) {
            return speakerService.getOneSpeaker(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PutMapping(Constants.SPEAKER_PUT)
    ResponseEntity<Speaker> updateSpeaker(@Valid @RequestBody Speaker speaker,
                                          @PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id) && (id == speaker.getId())) {
            return new ResponseEntity(speakerService.updateSpeaker(id, speaker), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping(Constants.SPEAKER_POST)
    Speaker createSpeaker(@Valid @RequestBody Speaker speaker) {
        return speakerService.createSpeaker(speaker);
    }

    // TODO: make an exception if the user attempts to delete a speaker within a session
    @DeleteMapping(Constants.SPEAKER_DELETE)
    void deleteSpeaker(@PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id)) {
            speakerService.deleteSpeaker(id);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
