package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.util.ViewsAPISessionIntegration;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class SessionService {
    private final ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();

    public SessionService() {}

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

