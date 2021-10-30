package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.repositories.MenteeRepository;
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
public class MenteeService {
    private final MenteeRepository menteeRepository;

    private static HttpURLConnection connection;

    private static final String SUCCESS = "Mentees Added to database";

    public MenteeService(MenteeRepository menteeRepository) {
        this.menteeRepository = menteeRepository;
    }

    public ArrayList<Mentee> getAllMenteesFromDatabase(){
        return (ArrayList<Mentee>) menteeRepository.findAll();
    }

    public String getAllMenteesFromViewsThenUpdateDatabase(){
        String response = getJsonMenteesFromViews();
        return parseMenteesJSON(response.toString());
    }

    public String getJsonMenteesFromViews(){
        int status = -1;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://app.viewsapp.net/api/restful/contacts/participants/search?");
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

    public String parseMenteesJSON(String responseBody){
        JSONObject body = new JSONObject(responseBody);

        String beginingKey = body.names().getString(0);
        JSONObject participants = body.getJSONObject(beginingKey);


        for(int i =0; i < participants.names().length() ; i++){
            System.out.println("key = " + participants.names().getString(i));
            String key = participants.names().getString(i);
            JSONObject participant = participants.getJSONObject(key);

            //We have the volunteer object, now fetch the required information.
            int viewsId = Integer.parseInt(participant.getString("PersonID"));
            String firstName = participant.getString("Forename");
            String lastName = participant.getString("Surname");

            Mentee mentee = new Mentee(viewsId,firstName,lastName);
            addMenteeToDatabase(mentee);
        }
        return SUCCESS;
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
}
