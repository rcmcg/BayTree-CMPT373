package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Authentication;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    private static HttpURLConnection connection;

    private static final String SUCCESS = "Mentors Added to database";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    public ArrayList<User> getAllMentorsFromDatabase(){
//        return (ArrayList<User>) userRepository.findAll();
//    }

    public String getAllUsersFromViewsThenUpdateDatabase(){
        String response = getJsonMentorsFromViews();
        return parse(response.toString());
    }

    public String getJsonMentorsFromViews(){
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
        return responseContent.toString();
    }

    public String parse(String responseBody){
        JSONObject body = new JSONObject(responseBody);

        String beginingKey = body.names().getString(0);
        JSONObject volunteers = body.getJSONObject(beginingKey);


        for(int i =0; i < volunteers.names().length() ; i++){
            System.out.println("key = " + volunteers.names().getString(i));
            String key = volunteers.names().getString(i);
            JSONObject volunteer = volunteers.getJSONObject(key);

            //We have the volunteer object, now fetch the required information.
            int viewsId = Integer.parseInt(volunteer.getString("PersonID"));
            String firstName = volunteer.getString("Forename");
            String lastName = volunteer.getString("Surname");
            String name = volunteer.getString("Name_V_53");
            String email = volunteer.getString("Email");
            String status = volunteer.getString("VolunteerStatus_V_1");
            String startDate = volunteer.getString("Startdate_V_37");
            String endDate = volunteer.getString("Enddate_V_38");
            String phoneNumber = volunteer.getString("Mobile_V_55");
            String ethnicity = volunteer.getString("Ethnicity_V_15");
            String address = volunteer.getString("Address1");
            String role = "Mentor";

            User user = new User(viewsId,firstName,lastName,name,email,status,startDate,endDate,phoneNumber,ethnicity,address,role);
            addMentorToDatabase(user);
        }
        return SUCCESS;
    }

    public void addMentorToDatabase(User mentor) {
        userRepository.save(mentor);
        System.out.println("Added mentor" + mentor.getViewsId());
    }
}
