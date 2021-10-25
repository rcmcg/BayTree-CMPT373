package com.baytree_mentoring.baytree_mentoring.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
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
    public static String getAllUsers(){
        int status = -1;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://app.viewsapp.net/api/restful/contacts/volunteers/search?");
            connection = (HttpURLConnection) url.openConnection();
            String userpass = "group.mercury" + ":" + "Mercury!$%12";
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

            //Request Setup
            connection.setRequestProperty ("Authorization", basicAuth);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
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
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parse(responseContent.toString());
        //return responseContent.toString();
    }

    public static String parse(String responseBody){
        JSONObject body = new JSONObject(responseBody);
        System.out.println(body.length());
        JSONObject volunteers = body.getJSONObject("volunteers count=\"15\"");
        System.out.println(volunteers.length());

        for(int i =0; i < volunteers.names().length() ; i++){
            System.out.println("key = " + volunteers.names().getString(i));
            String key = volunteers.names().getString(i);
            JSONObject volunteer = volunteers.getJSONObject(key);

            //We have the volunteer object, now fetch the required information.
            int viewsId = Integer.parseInt(volunteer.getString("PersonID"));
            String firstName = volunteer.getString("Forename");
            String lastNAme = volunteer.getString("Surname");
            String email = volunteer.getString("Email");
            String status = volunteer.getString("VolunteerStatus_V_1");
//            System.out.println(viewsId);
//            System.out.println(firstName);
//            System.out.println(lastNAme);
//            System.out.println(email);
//            System.out.println(status);
        }
        return volunteers.toString();
    }
}
