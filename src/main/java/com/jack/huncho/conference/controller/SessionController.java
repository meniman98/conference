package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.Constants;
import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.jack.huncho.conference.exception.FieldErrorMessage;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map
                (fieldError -> new FieldErrorMessage(fieldError.getField(),
                        fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
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
