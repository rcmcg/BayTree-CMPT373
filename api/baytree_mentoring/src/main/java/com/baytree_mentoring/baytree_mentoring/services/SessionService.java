package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.repositories.SessionRepository;
import com.google.gson.Gson;
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

    public void sendCompletedSessionFormToViews(Session ses) {
        System.out.println("Inside sendCompletedSessionFormToViews");
        System.out.println("Fields:");
        System.out.println(ses.getMenteeId());
        System.out.println(ses.getClockInTimeLocal());
        System.out.println(ses.getClockOutTimeLocal());
        System.out.println(ses.getSessionNotes());
        // createSessionFormJSONForViews(ses);
        // Assume we created the JSON object and are going to send it to Views
    }

    private void createSessionFormJSONForViews(Session ses) {
        Gson gson = new Gson();
        String[] values = {
                String.valueOf(ses.getMenteeId()),
                
        };
        // System.out.println(gson.toJson(ses));
    }
}

