package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
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

    public HttpResponse<String> sendUnirestGetRequest(String URL) throws UnirestException {
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
}
