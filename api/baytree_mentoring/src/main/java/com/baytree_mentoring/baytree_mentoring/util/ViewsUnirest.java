package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsUnirest {
    private static final String viewsAPIUsername = "group.mercury";
    private static final String viewsAPIPassword = "Mercury!$%12";

    public static String getViewsAPIUsername() {
        return viewsAPIUsername;
    }

    public static String getViewsAPIPassword() {
        return viewsAPIPassword;
    }

    public HttpResponse<String> sendUnirestGetRequestGetStringResponse(String URL) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            HttpResponse<String> response = Unirest.get(URL)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(viewsAPIUsername, viewsAPIPassword)
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

    public HttpResponse<JsonNode> sendUnirestGetRequestGetJsonNodeResponse(String URL) throws UnirestException {
        Unirest.setTimeouts(0,0);
        try {
            HttpResponse<JsonNode> response = Unirest.get(URL)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(viewsAPIUsername, viewsAPIPassword)
                    .asJson();
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

    public HttpResponse<String> sendUnirestPostRequest(String URL, String body) throws UnirestException {
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
                if (response.getStatus() == 501
                        && URL.matches("https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/[0-9]+/staff"))
                {
                    // Views returns 501 not implemented for URL:
                    // "https://app.viewsapp.net/api/restful/work/sessiongroups/sessions/<sessionID>/staff";
                    // This is incorrect. It is implemented and the endpoint works. So ignore the exception thrown.
                    System.out.println("sendUnirestPostRequest(): Caught incorrect 501 from Views for URL: " + URL);
                    System.out.println("Returning response without throwing exception");
                    return response;
                }
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

    public static HttpResponse<String> getJsonMentorsFromViews() throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/contacts/participants/search?q=";

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.get(URL)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth(viewsAPIUsername, viewsAPIPassword)
                    .asString();
            System.out.println(response.getBody());

            int status = response.getStatus();
            if (status < 200 || status >= 300) {
                String error = "Get request to " + URL + " failed";
                throw new UnirestException(error);
            } else {
                return response;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
