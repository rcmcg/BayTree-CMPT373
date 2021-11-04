package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsApiQuestionnaireIntegration {
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();
    private final ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter =
            new ViewsAPIQuestionnaireJSONFormatter();

    public String getMonthlyQuestionnaireFromViews(int mqViewsId) throws UnirestException {
        String questionnaireQuestionsUrl =
                "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + mqViewsId + "/questions";
        String questions = viewsUnirest.sendUnirestGetRequest(questionnaireQuestionsUrl).toString();
        return questions;
    }
}
