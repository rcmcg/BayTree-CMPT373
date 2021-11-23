package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.ViewsSession;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
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
        List<ViewsSession> sessionsList = new ArrayList<>();

        JSONObject body = new JSONObject(response.getBody());
        String beginningKey = body.names().getString(0);

        Object sessions = body.get(beginningKey); //Get plain object instead of JSONObject to avoid errors due to empty session data
        if (sessions instanceof JSONArray) { //sessions are empty
            return sessionsList;
        }
        else { // there are sessions
            JSONObject sessionsObject = (JSONObject) sessions; //Cast to JSONObject once it is confirmed there are sessions

            for(Object o: sessionsObject.names()) {
                JSONObject sessionObject = sessionsObject.getJSONObject(o.toString());

                ViewsSession session = buildSession(sessionObject);
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

        Object notes = body.get("notes"); //Get plain object instead of JSONObject to avoid errors due to empty notes
        if(notes instanceof JSONArray) { //notes is empty
            return "N/A";
        }
        else { // there are notes
            String note = "";
            if(notes instanceof JSONObject) {
                JSONObject notesObject = (JSONObject) notes; //Cast to JSONObject once it is confirmed there are notes
                for(Object o: notesObject.names()) {
                    JSONObject n = notesObject.getJSONObject(o.toString());
                    note += n.getString("Note") + "\n";
                }
            }
            return note;
        }
    }
}
