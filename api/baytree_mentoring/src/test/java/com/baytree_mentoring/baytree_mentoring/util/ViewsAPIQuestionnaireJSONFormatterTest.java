package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsAPIQuestionnaireJSONFormatterTest {
    ViewsApiQuestionnaireIntegration viewsApiQuestionnaireIntegration = new ViewsApiQuestionnaireIntegration();
    ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter = new ViewsAPIQuestionnaireJSONFormatter();

    @Test
    @Disabled
    void formatViewsQuestionnaireForFrontend() throws UnirestException {
        HttpResponse<JsonNode> questionnaireFromViews = viewsApiQuestionnaireIntegration.getMonthlyQuestionnaireFromViews(21);
        // String formattedJSONasString = viewsAPIQuestionnaireJSONFormatter.formatViewsQuestionnaireForFrontend(questionnaireFromViews);
    }

    @Test
    void createQuestionnaireUploadJSON() {
        MonthlyQuestionnaireSubmit mqSubmit = new MonthlyQuestionnaireSubmit(
               4,
               "12",
               "21",
                "2021-11-12T17:14:00",
                new String[]{"63", "64", "65", "66"},
                new String[]{"1", "2", "3", "4"}
        );
        String correctJSON = "{\"Date\":\"2021-11-12T17:14:00\",\"answers\":" +
                "{\"answer id=\\\"63\\\"\":{\"QuestionID\":63,\"Answer\":\"1\"}," +
                "\"answer id=\\\"64\\\"\":{\"QuestionID\":64,\"Answer\":\"2\"}," +
                "\"answer id=\\\"65\\\"\":{\"QuestionID\":65,\"Answer\":\"3\"}," +
                "\"answer id=\\\"66\\\"\":{\"QuestionID\":66,\"Answer\":\"4\"}}}";
        String uploadJSON = viewsAPIQuestionnaireJSONFormatter.createQuestionnaireUploadJSON(mqSubmit);
        assertEquals(correctJSON, uploadJSON);
    }
}