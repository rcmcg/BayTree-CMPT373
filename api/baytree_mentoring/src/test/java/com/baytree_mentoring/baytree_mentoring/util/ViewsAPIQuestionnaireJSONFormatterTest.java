package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ViewsAPIQuestionnaireJSONFormatterTest {
    ViewsApiQuestionnaireIntegration viewsApiQuestionnaireIntegration = new ViewsApiQuestionnaireIntegration();
    ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter = new ViewsAPIQuestionnaireJSONFormatter();

    @Test
    @Disabled
    void formatViewsQuestionnaireForFrontend() throws UnirestException {
        HttpResponse<JsonNode> questionnaireFromViews = viewsApiQuestionnaireIntegration.getMonthlyQuestionnaireFromViews(21);
        // String formattedJSONasString = viewsAPIQuestionnaireJSONFormatter.formatViewsQuestionnaireForFrontend(questionnaireFromViews);
    }
}