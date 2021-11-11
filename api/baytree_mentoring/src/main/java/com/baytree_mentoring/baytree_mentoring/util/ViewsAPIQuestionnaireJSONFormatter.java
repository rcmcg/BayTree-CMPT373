package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class ViewsAPIQuestionnaireJSONFormatter {
    public String formatViewsQuestionnaireForFrontend(HttpResponse<JsonNode> questions) {
        // Function unused currently, but will use in the future to modify the JSON before sending to the frontend
        System.out.println("ViewsAPIQuestionnaireJSONFormatter.formatViewsQuestionnaireForFrontend: Converting String to JSONObject" );
        System.out.println("formatViewsQuestionnaireForFrontend" + questions);
        System.out.println("formatViewsQuestionnaireForFrontend" + questions.getBody());
        System.out.println("formatViewsQuestionnaireForFrontend: Creating JSONObject from response");
        // JSONObject questionnaireJSONObject = (JSONObject) parser.parse(String.valueOf(questions.getBody()));
        JSONObject questionnaireJSONObject = new JSONObject(questions.getBody().toString());
        System.out.println("formatViewsQuestionnaireForFrontend: Finished conversion");
        System.out.println(questionnaireJSONObject);
        return questions.toString();
    }
}
