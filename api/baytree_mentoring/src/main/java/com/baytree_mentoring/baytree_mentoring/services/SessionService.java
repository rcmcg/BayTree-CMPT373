package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean isSessionFormComplete(Session ses) {
        if (ses.getClockOutTimeLocal().isEmpty()) {
            return false;
        }
        return true;
    }

    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }

    public boolean isSessionAdded(Session session) {
        return sessionRepository.existsById(session.getMentoringSessionId());
    }

    public void deleteSession(long mentoringSessionId) {
        sessionRepository.deleteById(mentoringSessionId);
    }

}

