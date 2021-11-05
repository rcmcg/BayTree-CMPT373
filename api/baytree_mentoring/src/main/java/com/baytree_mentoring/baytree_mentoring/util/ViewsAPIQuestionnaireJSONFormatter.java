package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class ViewsAPIQuestionnaireJSONFormatter {
    public String formatViewsQuestionnaireForFrontend(HttpResponse<JsonNode> questions) {
        System.out.println("ViewsAPIQuestionnaireJSONFormatter.formatViewsQuestionnaireForFrontend: Converting String to JSONObject" );
        System.out.println("formatViewsQuestionnaireForFrontend" + questions);
        System.out.println("formatViewsQuestionnaireForFrontend" + questions.getBody());
        return questions.toString();
    }
}
