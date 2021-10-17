package com.baytree_mentoring.baytree_mentoring.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ViewsAPIJSONFormatter {
    ObjectMapper mapper = new ObjectMapper();
    public String createSessionUploadJSON(String clockInTime, String clockOutTime, String leadStaff, String venueId) {
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
            return "";
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
}
