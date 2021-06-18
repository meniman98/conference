package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    Session createSessions(@RequestBody Session session) {
        return sessionService.createSession(session);
    }


    @PutMapping("/session/put/{id}")
    Session updateSession(@RequestBody Session session, @PathVariable long id) {
        return sessionService.updateSession(id, session);
    }

    @DeleteMapping("/session/delete/{id}")
    void deleteSession(@PathVariable long id) {
        sessionService.deleteSession(id);
    }
}
