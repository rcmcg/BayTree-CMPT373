package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ViewsAPISessionJSONFormatterTest {
    ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();
    ViewsAPISessionJSONFormatter viewsAPISessionJSONFormatter = new ViewsAPISessionJSONFormatter();
    @Test
    void formatSessionUploadJSONTest() throws ParseException {
        String clockInTime = "2021-09-28 20:12:12";
        String clockOutTime = "2021-09-28 21:12:12";
        String leadStaff = "28";
        String venueId = "2";
        String correctString = "{\"StartDate\":\"2021-09-28\",\"StartTime\":\"20:12:12\",\"Duration\":\"1\"," +
                "\"LeadStaff\":\"28\",\"VenueID\":\"2\"}";
        String body = viewsAPISessionJSONFormatter.createSessionUploadJSON(clockInTime, clockOutTime, leadStaff, venueId);
        assertEquals(correctString, body);
    }

    @Test
    void createSessionAttendanceJSONTest() {
        String viewsPersonId = "59";
        String attended = "1";
        String volunteering = "";
        String correctString = "{\"ContactID\":\""+viewsPersonId+"\"," +
                "\"Attended\":\""+attended+"\",\"Volunteering\":\""+volunteering+"\"}";
        String body = viewsAPISessionJSONFormatter.createSessionAttendanceJSON(viewsPersonId, attended);
        assertEquals(correctString, body);
    }

    @Disabled
    @Test
    void parseVenueIdFromSessionGroupGetResponseTest() {
        String mercuryTestSessionGroupId = "10";
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionGetURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/%s";
            HttpResponse<String> response = Unirest.get(String.format(viewsSessionGetURL, mercuryTestSessionGroupId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(viewsAPISessionIntegration.getViewsAPIUsername(), viewsAPISessionIntegration.getViewsAPIPassword())
                    .asString();
            System.out.println("Response inside getVenueIdForSessionGroupFromViews: ");
            System.out.println(response.getBody().toString());
            String venueId = ViewsAPISessionJSONFormatter.parseVenueIdFromSessionGroupGetResponse(response);
            assertEquals("2", venueId);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}