package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsAPISessionIntegration {
    private final ViewsAPIJSONFormatter viewsAPIJSONFormatter = new ViewsAPIJSONFormatter();
    private final String viewsAPIUsername = "group.mercury";
    private final String viewsAPIPassword = "Mercury!$%12";

    public String getViewsAPIUsername() {
        return viewsAPIUsername;
    }

    public String getViewsAPIPassword() {
        return viewsAPIPassword;
    }

    public final boolean sendCompletedSessionFormToViews(Session ses) {
        String viewsSessionId = uploadSessionInformation(ses);
        String menteeAttendanceResponse = uploadSessionAttendanceInformation(String.valueOf(ses.getMenteeId()), viewsSessionId);
        String mentorAttendanceResponse = uploadSessionAttendanceInformation(String.valueOf(ses.getMentorId()), viewsSessionId);
        uploadSessionNotes(ses);
        // System.out.println("Inside sendCompletedSessionFormToViews: ");
        // System.out.println("menteeAttendanceResponse: " + menteeAttendanceResponse.toString());
        // System.out.println("Inside sendCompletedSessionFormToViews: ");
        // System.out.println("mentorAttendanceResponse: " + mentorAttendanceResponse.toString());
        return false;
    }

    private String uploadSessionInformation(Session ses) {
        String venueId = getVenueIdForSessionGroupFromViews(String.valueOf(ses.getSessionGroupId()));
        String uploadJSON = viewsAPIJSONFormatter.createSessionUploadJSON(
                ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), String.valueOf(ses.getLeadStaffId()), venueId);
        System.out.println("uploadSessionInformation uploadJSON: " + uploadJSON);
        String viewsSessionId = sendSessionPostRequestGetNewSessionId(uploadJSON, ses.getSessionGroupId());
        return viewsSessionId;
    }

    String getVenueIdForSessionGroupFromViews(String sessionGroupId) {
        // Make a call to the Views API to find the venueId associated with the session group.
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionGetURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/%s";
            HttpResponse<String> response = Unirest.get(String.format(viewsSessionGetURL, sessionGroupId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(getViewsAPIUsername(), getViewsAPIPassword())
                    .asString();
            System.out.println("Response inside getVenueIdForSessionGroupFromViews: ");
            System.out.println(response.getBody().toString());
            return ViewsAPIJSONFormatter.parseVenueIdFromSessionGroupGetResponse(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String sendSessionPostRequestGetNewSessionId(String body, long sessionGroupId) {
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionPostURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/%s/sessions";
            HttpResponse<String> response = Unirest.post(String.format(viewsSessionPostURL, String.valueOf(sessionGroupId)))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .body(body)
                    .asString();
            System.out.println("Response inside sendSessionPostRequestGetNewSessionId: ");
            System.out.println(response.getBody().toString());
            return viewsAPIJSONFormatter.parseNewSessionIdFromSessionUploadResponse(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String uploadSessionAttendanceInformation(String viewsParticipantId, String viewsSessionId) {
        // Get proper JSON for updating attendance of mentor/mentee
        // TODO: Update hardcoded "1" attended. See Federica's Piazza response on recording missed sessions
        String attended = "1";
        String uploadAttendanceJSON = viewsAPIJSONFormatter.createSessionAttendanceJSON
                (viewsParticipantId, attended);
        // Send formatted JSON to Views for attendance of mentor/mentee
        String sendSessionAttendanceResponse = sendSessionAttendancePostRequest(uploadAttendanceJSON, viewsSessionId);
        return sendSessionAttendanceResponse;
    }

    private String sendSessionAttendancePostRequest(String uploadAttendanceJSON, String viewsSessionId) {
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionAttendancePostURL =
                    "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/%s/participants";
            HttpResponse<String> response = Unirest.post(String.format(viewsSessionAttendancePostURL, viewsSessionId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .body(uploadAttendanceJSON)
                    .asString();
            System.out.println("Response inside sendSessionAttendancePostRequest");
            System.out.println(response.getBody().toString());
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void uploadSessionNotes(Session ses) {
        // Get proper JSON for adding session notes to session
        // Send formatted JSON to Views for session notes
    }

    private void deleteSession(String viewsSessionId) {
        // Delete session
        // Used for testing when creating a new session (Create the session then delete it)
    }
}
