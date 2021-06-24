package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.Constants;
import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class SessionController {

    @Autowired
    SessionServiceImpl sessionService;

    @GetMapping(Constants.SESSION_GET)
    Iterable<Session> getAllSessions() {
        return sessionService.getSessions();
    }

    @GetMapping(Constants.SESSION_GET_ONE)
    Session getOneSession(@PathVariable long id) throws EntityNotFoundException {
        if (sessionService.isPresent(id)) {
            return sessionService.getOneSession(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping(Constants.SESSION_POST)
    Session createSessions(@Valid @RequestBody Session session) {
        return sessionService.createSession(session);
    }

    @PutMapping(Constants.SESSION_PUT)
    ResponseEntity<Session> updateSession(@Valid @RequestBody Session session,
                                          @PathVariable long id) throws EntityNotFoundException {
        if (sessionService.isPresent(id) && (id == session.getId())) {
            return new ResponseEntity(sessionService.updateSession(id, session), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping(Constants.SESSION_DELETE)
    void deleteSession(@PathVariable long id) throws EntityNotFoundException {
        if (sessionService.isPresent(id)) {
            sessionService.deleteSession(id);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
