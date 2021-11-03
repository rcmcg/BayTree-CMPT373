package com.baytree_mentoring.baytree_mentoring.services;

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
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private static HttpURLConnection connection;

    private static final String SUCCESS = "Mentors Added to database";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllMentorsFromDatabase(){
        return userRepository.findAll();
    }

    public void getAllMentorsFromViewsThenUpdateDatabase(){
        String response = getJsonMentorsFromViews();
        List<User> mentors = parseMentors(response);
        addListOfMentorsToDatabase(mentors);
    }

    public String getJsonMentorsFromViews(){
        int status;
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
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            line = reader.readLine();
            while (line != null){
                responseContent.append(line);
                line = reader.readLine();
            }
            reader.close();

            System.out.println(responseContent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent.toString();
    }

    public List<User> parseMentors(String responseBody){
        JSONObject body = new JSONObject(responseBody);

        String beginningKey = body.names().getString(0);
        JSONObject volunteers = body.getJSONObject(beginningKey);

        ArrayList<User> users = new ArrayList<>();
        for(Object o: volunteers.names()) {
            if (o instanceof JSONObject) { //JSONArrays can only be iterated over Object, but they should all be JSONObjects
                JSONObject volunteer = (JSONObject) o;

                User mentor = buildMentor(volunteer);
                users.add(mentor);
            }
        }
        return users;
    }

    public User buildMentor(JSONObject volunteer) {
        long viewsId = Integer.parseInt(volunteer.getString("PersonID"));
        String firstName = volunteer.getString("Forename");
        String lastName = volunteer.getString("Surname");
        String email = volunteer.getString("Email");
        String status = volunteer.getString("VolunteerStatus_V_1");
        String startDate = volunteer.getString("Startdate_V_37");
        String endDate = volunteer.getString("Enddate_V_38");
        String phoneNumber = volunteer.getString("Mobile_V_55");
        String ethnicity = volunteer.getString("Ethnicity_V_15");
        String address = volunteer.getString("Address1");
        String role = "Mentor";

        return new User(viewsId, firstName, lastName, email, status, startDate, endDate, phoneNumber, ethnicity, address, role);
    }

    public void addMentorToDatabase(User mentor) {
        userRepository.save(mentor);
        System.out.println("Added mentor" + mentor.getViewsId());
    }

    public void addListOfMentorsToDatabase(List<User> users) {
        userRepository.saveAll(users);
    }

    public boolean areMentorsAdded(List<User> users) {
        for (User user: users) {
            if (!userRepository.existsById(user.getViewsId())) {
                return false;
            }
        }
        return true;
    }
}
