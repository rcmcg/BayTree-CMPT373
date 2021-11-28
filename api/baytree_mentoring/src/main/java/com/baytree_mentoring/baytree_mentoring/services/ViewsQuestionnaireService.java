package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.ViewsQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewsQuestionnaireService {
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();
    private static final String NO_ANSWERS = "No key associated to answers found.";

    public int getNumberOfQuestionnairesByMentorId(long mentorId) throws UnirestException {
        HttpResponse<String> response = getJsonQuestionnaireFromViews(mentorId);

        if(response.getBody().equals("[]")) { //No questionnaires
            return 0;
        }
        JSONObject bodyObject = new JSONObject(response.getBody());

        return bodyObject.length();
    }

    public List<ViewsQuestionnaire> getQuestionnairesByMentorId(long mentorId) throws UnirestException {
        HttpResponse<String> response = getJsonQuestionnaireFromViews(mentorId);
        return parseQuestionnaires(response);
    }

    public HttpResponse<String> getJsonQuestionnaireFromViews(long mentorId) throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/contacts/volunteers/" + mentorId + "/questionnaires";
        return viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
    }

    public List<ViewsQuestionnaire> parseQuestionnaires(HttpResponse<String> response) throws UnirestException {
        List<ViewsQuestionnaire> questionnaireList = new ArrayList<>();

        if(response.getBody().equals("[]")) { //No questionnaires
            return questionnaireList;
        }
        JSONObject bodyObject = new JSONObject(response.getBody());

        for(Object o: bodyObject.names()) {
            JSONObject questionnaireObject = bodyObject.getJSONObject(o.toString());

            ViewsQuestionnaire questionnaire = buildQuestionnaire(questionnaireObject);
            questionnaireList.add(questionnaire);
        }
        return questionnaireList;
    }

    public ViewsQuestionnaire buildQuestionnaire(JSONObject questionnaire) throws UnirestException {
        long questionnaireId = questionnaire.getLong("QuestionnaireID");
        long answerSetId = questionnaire.getLong("AnswerSetID");

        String questionnaireName = questionnaire.getString("Questionnaire");
        String dateTime = questionnaire.getString("Date");

        List<String> questions = getQuestionnaireQuestions(questionnaireId);
        List<String> answers = getQuestionnaireAnswers(questionnaireId, answerSetId);
        return new ViewsQuestionnaire(questionnaireName, dateTime, questions, answers);
    }

    public List<String> getQuestionnaireQuestions(long questionnaireId) throws UnirestException{
        List<String> questionList = new ArrayList<>();

        String URL = "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + questionnaireId + "/questions";
        HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
        JSONObject body = new JSONObject(response.getBody());

        for(Object o: body.names()) {
            JSONObject questionObject = body.getJSONObject(o.toString());

            String question = questionObject.getString("Question");
            questionList.add(question);
        }
        return questionList;
    }

    public List<String> getQuestionnaireAnswers(long questionnaireId, long answerSetId) throws UnirestException{
        List<String> answerList = new ArrayList<>();

        String URL = "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + questionnaireId + "/answers/" + answerSetId;
        HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
        JSONObject body = new JSONObject(response.getBody());

        String answerKey = getAnswerKey(body);

        if(!answerKey.equals(NO_ANSWERS)) {
            JSONObject answersObject = body.getJSONObject(answerKey);
            for(Object o: answersObject.names()) {
                JSONObject sessionObject = answersObject.getJSONObject(o.toString());

                Object answer = sessionObject.get("Answer"); //Get plain object instead of string to bypass null answers throwing exception
                answerList.add(answer.toString()); //null can be safely converted to strings
            }
        }

        return answerList;
    }

    public String getAnswerKey(JSONObject body) {
        for(Object name: body.names()) {
            if(name.toString().contains("answers")) {
                return name.toString();
            }
        }
        return NO_ANSWERS;
    }
}
