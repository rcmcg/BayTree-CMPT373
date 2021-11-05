package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsApiQuestionnaireIntegration {
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();
    private final ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter =
            new ViewsAPIQuestionnaireJSONFormatter();

    public String getFormattedQuestionnaire(int mqViewsId) throws UnirestException {
        HttpResponse<JsonNode> questionnaireFromViews = getMonthlyQuestionnaireFromViews(mqViewsId);
        String formattedQuestionnaire = viewsAPIQuestionnaireJSONFormatter
                .formatViewsQuestionnaireForFrontend(questionnaireFromViews);
        System.out.println("ViewsApiQuestionnaireIntegration.getFormattedQuestionnaire: " +
                "formattedQuestions: " + formattedQuestionnaire);
        return "";
    }

    public HttpResponse<JsonNode> getMonthlyQuestionnaireFromViews(int mqViewsId) throws UnirestException {
        String questionnaireQuestionsUrl =
                "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + mqViewsId + "/questions";
        HttpResponse<JsonNode> questions = viewsUnirest
                .sendUnirestGetRequestGetJsonNodeResponse(questionnaireQuestionsUrl);
        return questions;
    }
}
