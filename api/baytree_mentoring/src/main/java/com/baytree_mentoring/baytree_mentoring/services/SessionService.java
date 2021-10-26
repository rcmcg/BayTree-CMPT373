package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.repositories.SessionRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
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

    public boolean sendCompletedSessionFormToViews(Session ses) {
        System.out.println("Sending session to Views database: " + ses.toString());
        try {
            viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses);
            return true;
        } catch (UnirestException | ParseException e) {
            System.out.println("Inside sendCompletedSessionFormToViews: Failed to upload session to Views.");
            e.printStackTrace();
            return false;
        }
    }
}

