package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Override
    public List<Session> getSessions() {
        return (List<Session>) repository.findAll();
    }

    @Override
    public Session getOneSession(long id) {
        Optional<Session> optionalSession = repository.findById(id);
        if (optionalSession.isPresent())
            return optionalSession.get();
        else
            // TODO: make a dedicated Exception class
            throw new RuntimeException("Session Not Found");
    }

    @Override
    public Session createSession(Session session) {
        return repository.save(session);
    }

    @Override
    public Session updateSession(long id, Session session) {
        Session existingSession = repository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "speaker_id");
        return repository.saveAndFlush(existingSession);
    }

    @Override
    public void deleteSession(long id) {
        repository.deleteById(id);
    }
}
