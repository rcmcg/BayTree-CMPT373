package com.baytree_mentoring.baytree_mentoring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ViewsAPISessionJSONFormatter {
    private ObjectMapper mapper = new ObjectMapper();

    // TODO: Replace parameters with a ses object when required fields are added
    public String createSessionUploadJSON(String clockInTime, String clockOutTime, String leadStaff, String venueId) throws ParseException {
        try {
            Date clockInDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clockInTime);
            Date clockOutDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clockOutTime);
            String startDate = clockInTime.substring(0,10);
            String startTime = clockInDate.toString().substring(11,19);
            long durationInMilliseconds = Math.abs(clockOutDate.getTime() - clockInDate.getTime());
            long duration = TimeUnit.HOURS.convert(durationInMilliseconds, TimeUnit.MILLISECONDS);
            return createSessionUploadJSONString(startDate, startTime, String.valueOf(duration), leadStaff, venueId);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String createSessionUploadJSONString(String startDate, String startTime, String duration, String leadStaff, String venueId) {
        ObjectNode sessionJSON = mapper.createObjectNode();
        sessionJSON.put("StartDate", startDate);
        sessionJSON.put("StartTime", startTime);
        sessionJSON.put("Duration", duration);
        sessionJSON.put("LeadStaff", leadStaff);
        sessionJSON.put("VenueID", venueId);
        return sessionJSON.toString();
    }

    public String parseNewSessionIdFromSessionUploadResponse(HttpResponse<String> response) {
        System.out.println("Inside parseNewSessionIdFromSessionUploadResponse");
        System.out.println(response.getBody());
        JSONObject responseJSON = new JSONObject(URLDecoder.decode(response.getBody(), StandardCharsets.UTF_8));
        String viewsSessionId = responseJSON.get("SessionID").toString();
        System.out.println("viewsSessionID: " + viewsSessionId);
        return viewsSessionId;
    }

    public String createSessionAttendanceJSON(String viewsPersonId, String attended) {
        // Required fields: viewsSessionId, viewsPersonId
        ObjectNode sessionJSON = mapper.createObjectNode();
        sessionJSON.put("ContactID", viewsPersonId);
        sessionJSON.put("Attended", attended);
        // TODO: Research how to properly use this Volunteering field
        sessionJSON.put("Volunteering", "");
        return sessionJSON.toString();
    }

    public static String parseVenueIdFromSessionGroupGetResponse(HttpResponse<String> response) {
        System.out.println("Inside parseVenueIdFromSessionGroupGetResponse");
        System.out.println(response.getBody());
        JSONObject responseJSON = new JSONObject(URLDecoder.decode(response.getBody(), StandardCharsets.UTF_8));
        String venueId = responseJSON.get("VenueID").toString();
        System.out.println("venueId: " + venueId);
        return venueId;
    }

    public String createSessionNotesUploadJSON(String notes) {
        ObjectNode sessionNotesJSON = mapper.createObjectNode();
        sessionNotesJSON.put("Note", notes);
        return sessionNotesJSON.toString();
    }
}
