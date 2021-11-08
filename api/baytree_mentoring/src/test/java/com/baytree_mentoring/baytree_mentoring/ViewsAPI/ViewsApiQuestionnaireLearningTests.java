package com.baytree_mentoring.baytree_mentoring.ViewsAPI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewsApiQuestionnaireLearningTests {

    @Test
    public void printAllQuestionnairesJSON() {
        String URL = "https://app.viewsapp.net/api/restful/evidence/questionnaires/search";
        HttpResponse<String> response = null;
        try {
            response = Unirest.get(URL)
                    .header("accept","application/json")
                    .basicAuth("group.mercury","Mercury!$%12")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert response != null;
        System.out.println(response.getBody());
        assertTrue(true);
    }
}
