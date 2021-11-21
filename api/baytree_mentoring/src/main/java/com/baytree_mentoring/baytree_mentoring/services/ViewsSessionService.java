package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.ViewsSession;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewsSessionService {
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();

    public List<ViewsSession> getSessionsByMentorId(long mentorId) throws UnirestException {
        HttpResponse<String> response = getJsonSessionsFromViews(mentorId);
        return parseSessions(response);
    }

    public HttpResponse<String> getJsonSessionsFromViews(long mentorId) throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/contacts/volunteers/" + mentorId + "/sessions";
        return viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
    }

    public List<ViewsSession> parseSessions(HttpResponse<String> response) throws UnirestException {
        JSONObject body = new JSONObject(response.getBody());

        String beginningKey = body.names().getString(0);
        JSONObject sessions = body.getJSONObject(beginningKey);

        List<ViewsSession> sessionsList = new ArrayList<>();
        for(Object o: sessions.names()) {
            if (o instanceof JSONObject) { //JSONArrays can only be iterated over Object, but they should all be JSONObjects
                JSONObject volunteer = (JSONObject) o;

                ViewsSession session = buildSession(volunteer);
                sessionsList.add(session);
            }
        }
        return sessionsList;
    }

    public ViewsSession buildSession(JSONObject session) throws UnirestException {
        long mentorId = session.getLong("ParticipantID");
        long sessionId = session.getLong("SessionID");

        String sessionGroup = session.getString("Title");
        String attendance = session.getString("Status");
        String dateTime = session.getString("StartDate");
        String duration = session.getString("Duration");

        String note = getSessionNote(sessionId);
        return new ViewsSession(mentorId, sessionGroup, attendance, dateTime, duration, note);
    }

    public String getSessionNote(long sessionId) throws UnirestException{
        String URL = "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" + sessionId + "/notes";
        HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
        JSONObject body = new JSONObject(response.getBody());

        String beginningKey = body.names().getString(0);
        JSONObject note = body.getJSONObject(beginningKey);

        return note.getString("notes");
    }
}
