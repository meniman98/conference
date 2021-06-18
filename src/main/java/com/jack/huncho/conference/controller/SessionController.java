package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.jack.huncho.conference.exception.FieldErrorMessage;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SessionController {

    @Autowired
    SessionServiceImpl sessionService;

    @GetMapping("/session/get")
    Iterable<Session> getAllSessions() {
        return sessionService.getSessions();
    }

    @GetMapping("/session/get/{id}")
    Session getOneSession(@PathVariable long id) {
        return sessionService.getOneSession(id);
    }

    @PostMapping("/session/post")
    Session createSessions(@Valid @RequestBody Session session) {
            return sessionService.createSession(session);
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

    @PutMapping("/session/put/{id}")
    ResponseEntity<Session> updateSession(@RequestBody Session session, @PathVariable long id) {
        if (sessionService.isPresent(id) && (id == session.getId())) {
            return new ResponseEntity(sessionService.updateSession(id, session), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(session, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/session/delete/{id}")
    void deleteSession(@PathVariable long id) {
        sessionService.deleteSession(id);
    }
}
