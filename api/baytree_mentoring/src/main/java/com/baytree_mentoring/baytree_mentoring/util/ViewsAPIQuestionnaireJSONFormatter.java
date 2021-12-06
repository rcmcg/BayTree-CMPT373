package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class ViewsAPIQuestionnaireJSONFormatter {
    private ObjectMapper mapper = new ObjectMapper();

    public String formatViewsQuestionnaireForFrontend(HttpResponse<JsonNode> questions) {
        JSONObject questionnaireJSONObject = new JSONObject(questions.getBody().toString());
        return questions.toString();
    }

    public String convertQuestionnaireToXML(MonthlyQuestionnaireSubmit mqSubmit) {
        String xmlString ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlString += "<answers>\n";
        xmlString += "\t<EntityType>" + "Participant" + "</EntityType>\n";
        xmlString += "\t<EntityID>" + mqSubmit.getMenteeId() + "</EntityID>\n";
        for (int i = 0; i < mqSubmit.getQuestionIds().size(); i++) {
            String questionId = mqSubmit.getQuestionIds().get(i);
            String answer = mqSubmit.getAnswers().get(i);

            xmlString += "\t<answer id=" + "\"" + parseIdFromKey(questionId) + "\">\n";
            xmlString += "\t\t<QuestionID>" + parseIdFromKey(questionId) + "</QuestionID>\n";
            xmlString += "\t\t<Answer>" + answer + "</Answer>\n";
            xmlString += "\t</answer>\n";
        }
        xmlString += "</answers>\n";
        return xmlString;
    }

    private String parseIdFromKey(String key) {
        // Key has form "question id=\"54\". Parse the id from it
        int indexFirstDigit = 13;
        int indexLastDigit = -1;
        for (int i = indexFirstDigit; i < key.length(); i++) {
            if (!Character.isDigit(key.charAt(i))) {
                indexLastDigit = i - 1;
            }
        }
        return key.substring(indexFirstDigit, indexLastDigit + 1);
    }
}
