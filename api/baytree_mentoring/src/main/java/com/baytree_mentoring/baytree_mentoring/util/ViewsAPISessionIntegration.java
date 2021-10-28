package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.text.ParseException;

public class ViewsAPISessionIntegration {
    private final ViewsAPIJSONFormatter viewsAPIJSONFormatter = new ViewsAPIJSONFormatter();
    public String viewsAPIUsername = "group.mercury";
    public String viewsAPIPassword = "Mercury!$%12";

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
            System.out.println("sendCompletedSessionFormToViews: Failed to parse JSON for session upload");
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
        String viewsSessionGetURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/" + sessionGroupId;
        try {
            HttpResponse<String> sessionGroupGetResponse = sendUnirestGetRequest(viewsSessionGetURL);
            String venueId = ViewsAPIJSONFormatter.parseVenueIdFromSessionGroupGetResponse(sessionGroupGetResponse);
            return venueId;
        } catch (UnirestException e) {
            System.out.println("Inside getVenueIdForSessionGroupFromViews:");
            System.out.println("Failed to parse viewsSessionId, throw exception");
            e.printStackTrace();
            throw e;
        }
    }

    private String sendSessionPostRequestGetNewSessionId(String body, long sessionGroupId) throws UnirestException {
        String viewsSessionPostURL =
                "https://app.viewsapp.net/api/restful/work/sessiongroups/" + sessionGroupId + "/sessions";
        try {
            HttpResponse<String> sessionPostResponse = sendUnirestPostRequest(viewsSessionPostURL, body);
            return viewsAPIJSONFormatter.parseNewSessionIdFromSessionUploadResponse(sessionPostResponse);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void uploadSessionAttendanceInformation(String viewsParticipantId, String viewsSessionId, boolean attended)
            throws UnirestException {
        String stringAttended;
        if (attended) {
            stringAttended = "1";
        } else {
            stringAttended = "0";
        }
        try {
            String uploadAttendanceJSON = viewsAPIJSONFormatter.createSessionAttendanceJSON
                    (viewsParticipantId, stringAttended);
            String viewsSessionAttendancePostURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" +
                            viewsSessionId +
                            "/participants";
            sendUnirestPostRequest(viewsSessionAttendancePostURL, uploadAttendanceJSON);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void uploadSessionNotes(Session ses, String viewsSessionId) throws UnirestException {
        String uploadSessionNotesJSON = viewsAPIJSONFormatter.createSessionNotesUploadJSON(ses.getSessionNotes());
        String sessionNotesPostURL =
                "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" + viewsSessionId + "/notes";
        sendUnirestPostRequest(sessionNotesPostURL, uploadSessionNotesJSON);
    }

    private HttpResponse<String> sendUnirestGetRequest(String URL) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            HttpResponse<String> response = Unirest.get(URL)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(this.viewsAPIUsername, this.viewsAPIPassword)
                    .asString();
            System.out.println(response.getBody());
            if (httpResponseIsNotOk(response.getStatus())) {
                String error = "Failed to send/receive get request to " + URL;
                throw new UnirestException(error);
            } else {
                return response;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private HttpResponse<String> sendUnirestPostRequest(String URL, String body) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            HttpResponse<String> response = Unirest.post(URL)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(viewsAPIUsername, viewsAPIPassword)
                    .body(body)
                    .asString();
            System.out.println(response.getBody());
            if (httpResponseIsNotOk(response.getStatus())) {
                String error = "Post request to " + URL + " failed";
                throw new UnirestException(error);
            } else {
                return response;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private boolean httpResponseIsNotOk(int statusCode) {
        return statusCode < 200 || statusCode >= 300;
    }

    private void deleteSession(String viewsSessionId) {
        // Delete session
        // Will be used for testing when creating a new session (Create the session then delete it)
    }
}
