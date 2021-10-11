package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.repositories.SessionRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void sendCompletedSessionFormToViews(Session ses) {
        // hard code in 28 (Mercury Team) and 2 for Venue ID, for now
        Unirest.setTimeouts(0,0);
        String body = formatSessionUploadJson(ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), "28", "2");
        try {
            // hardcode upload to our test group for now
            HttpResponse<String> response = Unirest.post("https://app.viewsapp.net/api/restful/work/sessiongroups/10/sessions")
                    .header("Content-Type", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .body(body)
                    .asString();
            System.out.println(response.getBody().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public String formatSessionUploadJson(String clockInTime, String clockOutTime, String leadStaff, String venueId) {
        // convert clockInTime 2021-10-10 22:14:00 -0930 YYYY-MM-DD
        Date clockInDate = null;
        Date clockOutDate = null;
        try {
            clockInDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clockInTime);
            clockOutDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clockOutTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String startDate = clockInTime.substring(0,10);
        String startTime = clockInDate.toString().substring(11,19);
        long durationInMilliseconds = Math.abs(clockOutDate.getTime() - clockInDate.getTime());
        long duration = TimeUnit.HOURS.convert(durationInMilliseconds, TimeUnit.MILLISECONDS);
        String body = "{\r\n   \"StartDate\": \""+startDate+"\",\r\n   \"StartTime\": \""+startTime+"\",\r\n   \"Duration\": \""+duration+"\",\r\n   \"LeadStaff\": \""+leadStaff+"\",\r\n   \"VenueID\": \""+venueId+"\"\r\n}";
        return body;
    }

}

