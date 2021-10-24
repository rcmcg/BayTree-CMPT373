package com.baytree_mentoring.baytree_mentoring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;


import java.util.List;

@RestController
public class UsersController {
    private static HttpURLConnection connection;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/mentors")
    public static int getAllUsers(){
        int status = -1;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://app.viewsapp.net/api/restful/contacts/volunteers/search?");
            connection = (HttpURLConnection) url.openConnection();
            String userpass = "group.mercury" + ":" + "Mercury!$%12";
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

            connection.setRequestProperty ("Authorization", basicAuth);
            //Request Setup
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            status = connection.getResponseCode();
            System.out.println(status);

            if (status>200){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
            } else {

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;

    }
}
