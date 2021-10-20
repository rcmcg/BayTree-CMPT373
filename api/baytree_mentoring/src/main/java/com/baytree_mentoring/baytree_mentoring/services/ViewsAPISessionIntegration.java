package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsAPISessionIntegration {
    private final ViewsAPIJSONFormatter viewsAPIJSONFormatter = new ViewsAPIJSONFormatter();

    public final boolean sendCompletedSessionFormToViews(Session ses) {
        String viewsSessionId = uploadSessionInformation(ses);

        // TODO: Add mentorId to Session class
        // uploadSessionAttendanceInformation(ses);
        // uploadSessionAttendanceInformation(ses);

        // Mercury Mentor has id 42
        int mentorId = 42;
        String menteeAttendanceResponse = uploadSessionAttendanceInformation(String.valueOf(ses.getMenteeId()), viewsSessionId);
        System.out.println("Inside sendCompletedSessionFormToViews: ");
        System.out.println("menteeAttendanceResponse: " + menteeAttendanceResponse.toString());
        String mentorAttendanceResponse = uploadSessionAttendanceInformation(String.valueOf(mentorId), viewsSessionId);
        System.out.println("Inside sendCompletedSessionFormToViews: ");
        System.out.println("mentorAttendanceResponse: " + mentorAttendanceResponse.toString());
        uploadSessionNotes(ses);
        return false;
    }

    private String uploadSessionInformation(Session ses) {
        // TODO: Replace leadStaff and venueId with dynamic values added to Session object from user input on frontend
        String uploadJSON = viewsAPIJSONFormatter.createSessionUploadJSON(ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), "28", "2");
        System.out.println("uploadSessionInformation uploadJSON: " + uploadJSON);
        // sendSessionPostRequest(uploadJSON, ses.getSessionGroupId());
        // Hardcode sessionGroupId as 10 (Mercury Test Group) for now
        String viewsSessionId = sendSessionPostRequestGetNewSessionId(uploadJSON, 10);
        return viewsSessionId;
    }

    private String sendSessionPostRequestGetNewSessionId(String body, int sessionGroupId) {
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
