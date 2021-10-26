package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.text.ParseException;
import java.util.Objects;

public class ViewsAPISessionIntegration {
    private final ViewsAPIJSONFormatter viewsAPIJSONFormatter = new ViewsAPIJSONFormatter();

    public String getViewsAPIUsername() {
        String viewsAPIUsername = "group.mercury";
        return viewsAPIUsername;
    }

    public String getViewsAPIPassword() {
        String viewsAPIPassword = "Mercury!$%12";
        return viewsAPIPassword;
    }

    public final void sendCompletedSessionFormToViews(Session ses) throws UnirestException, ParseException {
        try {
            // TODO: Check that the mentee and mentor exist before creating the session,
            //  otherwise the session is created then fails to update attendance, creating a useless session upload.
            String viewsSessionId = uploadSessionInformationGetSessionId(ses);
            uploadSessionAttendanceInformation(
                    String.valueOf(ses.getMenteeId()), viewsSessionId, ses.isDidMenteeAttend());
            uploadSessionAttendanceInformation(
                    String.valueOf(ses.getMentorId()), viewsSessionId, ses.isDidMentorAttend());
            uploadSessionNotes(ses, viewsSessionId);
        } catch (UnirestException e) {
            System.out.println("sendCompletedSessionFormToViews: Failed to upload session to Views database");
            e.printStackTrace();
            throw e;
        } catch (ParseException e) {
            System.out.println("sendCompletedSessionFormToViews: Failed to parse JSON");
            e.printStackTrace();
            throw e;
        }
    }

    private String uploadSessionInformationGetSessionId(Session ses) throws UnirestException, ParseException {
        String venueId = getVenueIdForSessionGroupFromViews(String.valueOf(ses.getSessionGroupId()));
        String uploadJSON = viewsAPIJSONFormatter.createSessionUploadJSON(
                ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), String.valueOf(ses.getLeadStaffId()), venueId);
        String viewsSessionId = sendSessionPostRequestGetNewSessionId(uploadJSON, ses.getSessionGroupId());
        return viewsSessionId;
    }

    String getVenueIdForSessionGroupFromViews(String sessionGroupId) throws UnirestException {
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
            System.out.println(response.getBody());
            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                return ViewsAPIJSONFormatter.parseVenueIdFromSessionGroupGetResponse(response);
            } else {
                String error = "Failed to get session Id from Views";
                throw new UnirestException(error);
            }
        } catch (UnirestException e) {
            System.out.println("Inside getVenueIdForSessionGroupFromViews:");
            System.out.println("Failed to parse viewsSessionId, throw exception");
            e.printStackTrace();
            throw e;
        }
    }

    private String sendSessionPostRequestGetNewSessionId(String body, long sessionGroupId) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionPostURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/%s/sessions";
            HttpResponse<String> response = Unirest.post(String.format(viewsSessionPostURL, sessionGroupId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .body(body)
                    .asString();
            System.out.println("Response inside sendSessionPostRequestGetNewSessionId: ");
            System.out.println(response.getBody());
            return viewsAPIJSONFormatter.parseNewSessionIdFromSessionUploadResponse(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String uploadSessionAttendanceInformation(String viewsParticipantId, String viewsSessionId, boolean attended) throws UnirestException {
        // Get proper JSON for updating attendance of mentor/mentee
        String stringAttended;
        if (attended) {
            stringAttended = "1";
        } else {
            stringAttended = "0";
        }
        try {
            String uploadAttendanceJSON = viewsAPIJSONFormatter.createSessionAttendanceJSON
                    (viewsParticipantId, stringAttended);
            // Send formatted JSON to Views for attendance of mentor/mentee
            String sendSessionAttendanceResponse = sendSessionAttendancePostRequest(uploadAttendanceJSON, viewsSessionId);
            return sendSessionAttendanceResponse;
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String sendSessionAttendancePostRequest(String uploadAttendanceJSON, String viewsSessionId) throws UnirestException {
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
            System.out.println(response.getBody());
            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                return response.getBody();
            } else {
                String error = "Failed to post session attendance to Views";
                throw new UnirestException(error);
            }
        } catch (UnirestException e) {
            System.out.println("Inside sendSessionAttendancePostRequest:");
            System.out.println("Failed to post session attendance to Views");
            e.printStackTrace();
            throw e;
        }
    }

    private String uploadSessionNotes(Session ses, String viewsSessionId) throws UnirestException {
        // Get proper JSON for adding session notes to session
        String uploadSessionNotesJSON = viewsAPIJSONFormatter.createSessionNotesUploadJSON(ses.getSessionNotes());
        // Send formatted JSON to Views for session notes
        String sendSessionNotesResponse = sendSessionNotesPostRequest(uploadSessionNotesJSON, viewsSessionId);
        return sendSessionNotesResponse;
    }

    private String sendSessionNotesPostRequest(String uploadSessionNotesJSON, String viewsSessionId) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            String viewsSessionNotesPostURL =
                    "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/%s/notes";
            HttpResponse<String> response = Unirest.post(String.format(viewsSessionNotesPostURL, viewsSessionId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(getViewsAPIUsername(), getViewsAPIPassword())
                    .body(uploadSessionNotesJSON)
                    .asString();
            System.out.println("Response inside sendSessionNotesPostRequest");
            System.out.println(response.getBody());
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void deleteSession(String viewsSessionId) {
        // Delete session
        // Used for testing when creating a new session (Create the session then delete it)
    }
}
