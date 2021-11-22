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

    public List<ViewsQuestionnaire> getQuestionnairesByMentorId(long mentorId) throws UnirestException {
        HttpResponse<String> response = getJsonQuestionnaireFromViews(mentorId);
        return parseQuestionnaires(response);
    }

    public HttpResponse<String> getJsonQuestionnaireFromViews(long mentorId) throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/contacts/volunteers/" + mentorId + "/questionnaires";
        return viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
    }

    public List<ViewsQuestionnaire> parseQuestionnaires(HttpResponse<String> response) throws UnirestException {
        if(response.getBody().equals("[]")) {
            return new ArrayList<>();
        }
        JSONObject body = new JSONObject(response.getBody());

        List<ViewsQuestionnaire> questionnaireList = new ArrayList<>();
        for(Object o: body.names()) {
            JSONObject questionnaireObject = body.getJSONObject(o.toString());

            ViewsQuestionnaire questionnaire = buildQuestionnaire(questionnaireObject);
            questionnaireList.add(questionnaire);
        }
        return questionnaireList;
    }

    public ViewsQuestionnaire buildQuestionnaire(JSONObject quesitonnaire) throws UnirestException {
        long mentorId = quesitonnaire.getLong("EntityID");
        long questionnaireId = quesitonnaire.getLong("QuestionnaireID");
        long answerSetId = quesitonnaire.getLong("AnswerSetID");

        String questionnaireName = quesitonnaire.getString("Questionnaire");
        String dateTime = quesitonnaire.getString("Date");

        List<String> questions = getQuestionnaireQuestions(questionnaireId);
        List<String> answers = getQuestionnaireAnswers(mentorId, questionnaireId, answerSetId);
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

    public List<String> getQuestionnaireAnswers(long mentorId, long questionnaireId, long answerSetId) throws UnirestException{
        List<String> answerList = new ArrayList<>();

        String URL = "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + questionnaireId + "/answers/" + answerSetId;
        HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
        JSONObject body = new JSONObject(response.getBody());

        String answerSetKey = (String) body.keySet().toArray()[body.length() - 3];
        JSONObject answersObject = body.getJSONObject(answerSetKey);
        for(Object o: answersObject.names()) {
            JSONObject sessionObject = answersObject.getJSONObject(o.toString());
            String answer = sessionObject.getString("Answer");
            answerList.add(answer);
        }
        return answerList;
    }
}
