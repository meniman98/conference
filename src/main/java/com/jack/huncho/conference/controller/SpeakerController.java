package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.exception.FieldErrorMessage;
import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.service.SpeakerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpeakerController {

    @Autowired
    SpeakerServiceImpl speakerService;

    // TODO: make an exception when an empty list is returned
    @GetMapping("/speaker/get")
    Iterable<Speaker> getAllSpeakers() {
        return speakerService.getSpeakers();
    }

    @GetMapping("/speaker/get/{id}")
    Speaker getOneSpeaker(@PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id)) {
            return speakerService.getOneSpeaker(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PutMapping("/speaker/put/{id}")
    ResponseEntity<Speaker> updateSpeaker(@Valid @RequestBody Speaker speaker,
                                          @PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id) && (id == speaker.getId())) {
            return new ResponseEntity(speakerService.updateSpeaker(id, speaker), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("/speaker/post")
    Speaker createSpeaker(@Valid @RequestBody Speaker speaker) {
        return speakerService.createSpeaker(speaker);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map
                (fieldError -> new FieldErrorMessage(fieldError.getField(),
                        fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    // TODO: make an exception if the user attempts to delete a speaker within a session
    @DeleteMapping("/speaker/delete/{id}")
    void deleteSpeaker(@PathVariable long id) throws EntityNotFoundException {
        if (speakerService.isPresent(id)) {
            speakerService.deleteSpeaker(id);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
