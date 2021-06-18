package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> getSessions();
    Session getOneSession(long id);
    Session createSession(Session session);
    Session updateSession(long id, Session session);
    void deleteSession(long id);
    boolean isPresent(long id);
}
