package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Override
    public List<Session> getSessions(){
        return (List<Session>) repository.findAll();
    }
}
