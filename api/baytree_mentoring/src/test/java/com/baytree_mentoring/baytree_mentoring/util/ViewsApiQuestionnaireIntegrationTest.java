package com.baytree_mentoring.baytree_mentoring.util;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsApiQuestionnaireIntegrationTest {
    private static ViewsUnirest viewsUnirest = new ViewsUnirest();

    @Test
    void getMonthlyQuestionnaireFromViews() throws UnirestException {
        int mqViewsId = 18;
        String questionnaireQuestionsUrl =
                "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + mqViewsId + "/questions";
        String questions = viewsUnirest.sendUnirestGetRequestGetStringResponse(questionnaireQuestionsUrl).toString();
        System.out.println(questions);
        assertTrue(true);
    }
}