package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.ViewsMentee;
import com.baytree_mentoring.baytree_mentoring.repositories.MenteeRepository;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenteeService {
    private final MenteeRepository menteeRepository;
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();

    public MenteeService(MenteeRepository menteeRepository) {
        this.menteeRepository = menteeRepository;
    }

    public List<Mentee> getAllMenteesFromDatabase(){
        return menteeRepository.findAll();
    }

    public void getAllMenteesFromViewsThenUpdateDatabase(){
        String response = getJsonMenteesFromViews();
        List<Mentee> mentees = parseMenteesJSON(response);

        for(Mentee mtr:mentees){
            addMenteeToDatabase(mtr);
        }
    }

    public String getJsonMenteesFromViews(){
        int status;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL("https://app.viewsapp.net/api/restful/contacts/participants/search?");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            System.out.println(responseContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent.toString();
    }

    public List<Mentee> parseMenteesJSON(String responseBody){
        JSONObject body = new JSONObject(responseBody);

        String beginingKey = body.names().getString(0);
        JSONObject participants = body.getJSONObject(beginingKey);
        List<Mentee> mentees = new ArrayList<>();


        for(int i =0; i < participants.names().length() ; i++){
            System.out.println("key = " + participants.names().getString(i));
            String key = participants.names().getString(i);
            JSONObject participant = participants.getJSONObject(key);

            //We have the volunteer object, now fetch the required information.
            Long viewsId = Long.parseLong(participant.getString("PersonID"));
            String firstName = participant.getString("Forename");
            String lastName = participant.getString("Surname");

            Mentee mentee = new Mentee(viewsId,firstName,lastName);
            mentees.add(mentee);

        }
        return mentees;
    }

    public void addMenteeToDatabase(Mentee mentee) {
        menteeRepository.save(mentee);
        System.out.println("Added mentee" + mentee.getMenteeId());
    }

    public void add(Mentee mentee) {
        menteeRepository.save(mentee);
    }


    public boolean isMenteeAdded(Mentee mentee) {
        return menteeRepository.existsById(mentee.getMenteeId());
    }

    public List<Optional<ViewsMentee>> getMenteesFromViews() {
        String URL = "https://app.viewsapp.net/api/restful/contacts/participants/search?q=";

        List<Optional<ViewsMentee>> mentees = new ArrayList<>();

        try {
            HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
            mentees = parseMentees(response);
        } catch(UnirestException e) {
            e.printStackTrace();
            return mentees;
        }

        return mentees;
    }

    private List<Optional<ViewsMentee>> parseMentees(HttpResponse<String> response) {
        JSONObject body = new JSONObject(response.getBody());
        String count = body.names().getString(0);
        JSONObject menteeIds = body.getJSONObject(count);

        List<Optional<ViewsMentee>> mentees = new ArrayList<>();

        for(Object o : menteeIds.names()) {
            JSONObject mentee = menteeIds.getJSONObject(o.toString());
            ViewsMentee viewsMentee = buildMentee(mentee, o);
            mentees.add(Optional.of(viewsMentee));
        }

        System.out.println("MENTEES: " + mentees.size());
        return mentees;
    }

    private ViewsMentee buildMentee(JSONObject mentee, Object id) {
        long participantId = Long.parseLong(extractId(id.toString()));
        String firstName = mentee.getString("Forename");
        String lastName = mentee.getString("Surname");

        return new ViewsMentee(participantId, firstName, lastName);
    }

    private String extractId(String id) {
        id = id.replaceAll("participant id=", "");
        id = id.replaceAll("\"", "");

        return id;
    }
}
