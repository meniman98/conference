package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.exception.ErrorMessage;
import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

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
    Session createSessions(@RequestBody Session session) throws ValidationException {
        if (session.getName() != null) {
            return sessionService.createSession(session);
        }
        else {
            throw new ValidationException("Session not valid, needs a name and ID");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage(), "400");
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
