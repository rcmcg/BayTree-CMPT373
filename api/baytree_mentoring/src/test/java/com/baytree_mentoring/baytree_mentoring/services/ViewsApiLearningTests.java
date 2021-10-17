package com.baytree_mentoring.baytree_mentoring.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewsApiLearningTests {

    @Test
    public void printAllViewsParticipantsJSON() {
        String URL = "https://app.viewsapp.net/api/restful/contacts/participants/search?q=";
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(URL)
                .header("accept", "application/json")
                .basicAuth("group.mercury", "Mercury!$%12")
                .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert response != null;
        System.out.println(response.getBody());
    }

    @Test
    public void printOneParticipantByPersonIDJSON() {
        // Use the above test to pick a particular participant to print by PersonID
        String URL = "https://app.viewsapp.net/api/restful/contacts/participants/search";
        int personID = 9;
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(URL)
                    .header("accept", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .queryString("PersonID", String.valueOf(9))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert response != null;
        System.out.println(response.getBody());
    }

}
