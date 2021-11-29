package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private static final String viewsAPIUsername = "group.mercury";
    private static final String viewsAPIPassword = "Mercury!$%12";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getMentorById(long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllMentorsFromDatabase(){
        return userRepository.findAll();
    }

    public boolean getAllMentorsFromViewsThenUpdateDatabase(){
        try {
            HttpResponse<String> response = getJsonMentorsFromViews();
            List<User> mentors = parseMentors(response);
            addListOfMentorsToDatabase(mentors);

            return true;
        } catch (UnirestException e) {
            e.printStackTrace();

            return false;
        }
    }

    public HttpResponse<String> getJsonMentorsFromViews() throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/contacts/volunteers/search?";

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

    public List<User> parseMentors(HttpResponse<String> response){
        JSONObject body = new JSONObject(response.getBody());

        String beginningKey = body.names().getString(0);
        JSONObject volunteers = body.getJSONObject(beginningKey);

        List<User> users = new ArrayList<>();
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
        long viewsId = volunteer.getLong("PersonID");
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
    }

    public void addListOfMentorsToDatabase(List<User> users) {
        userRepository.saveAll(users);
    }

    public boolean isMentorAdded(User user) {
        return userRepository.existsById(user.getViewsId());
    }

    public boolean areMentorsAdded(List<User> users) {
        for (User user: users) {
            if (!userRepository.existsById(user.getViewsId())) {
                return false;
            }
        }
        return true;
    }

    public void writeToCsvById(long id)  {
        User user = getMentorById(id).get();

        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        CsvSchema.Builder schemaBuilder = CsvSchema.builder().setUseHeader(true);
        for (Field field: user.getClass().getDeclaredFields()) {
            schemaBuilder.addColumn(field.getName());
        }
        CsvSchema schema = schemaBuilder.build();

        ObjectWriter writer = mapper.writerFor(user.getClass()).with(schema);
        try {
            writer.writeValue(System.out, user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
