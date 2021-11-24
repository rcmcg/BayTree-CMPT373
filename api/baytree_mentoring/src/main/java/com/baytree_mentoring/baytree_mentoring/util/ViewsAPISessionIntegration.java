package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.text.ParseException;

public class ViewsAPISessionIntegration {
    private final ViewsAPISessionJSONFormatter viewsAPISessionJSONFormatter = new ViewsAPISessionJSONFormatter();
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();

    public String getViewsAPIUsername() {
        return ViewsUnirest.getViewsAPIUsername();
    }

    public String getViewsAPIPassword() {
        return ViewsUnirest.getViewsAPIPassword();
    }

    public final void sendCompletedSessionFormToViews(Session ses) throws UnirestException, ParseException {
        try {
            // TODO: Check that the mentee and mentor exist before creating the session,
            //  otherwise the session is created then fails to update attendance, creating a useless session upload.
            String viewsSessionId = uploadSessionInformationGetSessionId(ses);
            if (!ses.isDidMenteeAttend() || !ses.isDidMentorAttend()) {
                // Exactly both or 0 participants attended the session
                ses.setDidMenteeAttend(false);
                ses.setDidMentorAttend(false);
            }
            String participantAttendanceURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" +
                    viewsSessionId +
                    "/participants";
            String mentorAttendanceURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" +
                    viewsSessionId +
                    "/staff";
            uploadSessionAttendanceInformation(
                    String.valueOf(ses.getMenteeId()), ses.isDidMenteeAttend(), participantAttendanceURL);
            uploadSessionAttendanceInformation(
                    String.valueOf(ses.getMentorId()), ses.isDidMentorAttend(), mentorAttendanceURL);
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
        String uploadJSON = viewsAPISessionJSONFormatter.createSessionUploadJSON(
                ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), String.valueOf(ses.getMentorId()), venueId);
        String viewsSessionId = sendSessionPostRequestGetNewSessionId(uploadJSON, ses.getSessionGroupId());
        return viewsSessionId;
    }

    String getVenueIdForSessionGroupFromViews(String sessionGroupId) throws UnirestException {
        // Make a call to the Views API to find the venueId associated with the session group.
        String viewsSessionGetURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/" + sessionGroupId;
        try {
            HttpResponse<String> sessionGroupGetResponse = viewsUnirest.sendUnirestGetRequestGetStringResponse(viewsSessionGetURL);
            String venueId = ViewsAPISessionJSONFormatter.parseVenueIdFromSessionGroupGetResponse(sessionGroupGetResponse);
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
            HttpResponse<String> sessionPostResponse = viewsUnirest.sendUnirestPostRequest(viewsSessionPostURL, body);
            return viewsAPISessionJSONFormatter.parseNewSessionIdFromSessionUploadResponse(sessionPostResponse);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void uploadSessionAttendanceInformation(String viewsParticipantId, boolean attended, String URL)
            throws UnirestException {
        String stringAttended;
        if (attended) {
            stringAttended = "1";
        } else {
            stringAttended = "0";
        }
        try {
            String uploadAttendanceJSON = viewsAPISessionJSONFormatter.createSessionAttendanceJSON
                    (viewsParticipantId, stringAttended);
            viewsUnirest.sendUnirestPostRequest(URL, uploadAttendanceJSON);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void uploadSessionNotes(Session ses, String viewsSessionId) throws UnirestException {
        String uploadSessionNotesJSON = viewsAPISessionJSONFormatter.createSessionNotesUploadJSON(ses.getSessionNotes());
        String sessionNotesPostURL =
                "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/" + viewsSessionId + "/notes";
        viewsUnirest.sendUnirestPostRequest(sessionNotesPostURL, uploadSessionNotesJSON);
    }

    private void deleteSession(String viewsSessionId) {
        // Delete session
        // Will be used for testing when creating a new session (Create the session then delete it)
    }
}
