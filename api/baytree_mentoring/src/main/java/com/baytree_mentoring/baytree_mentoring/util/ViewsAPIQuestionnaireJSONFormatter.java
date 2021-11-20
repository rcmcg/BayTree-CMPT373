package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

import java.util.Hashtable;

public class ViewsAPIQuestionnaireJSONFormatter {
    private ObjectMapper mapper = new ObjectMapper();

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

    public String createQuestionnaireUploadJSON(MonthlyQuestionnaireSubmit mqSubmit) {
        ObjectNode questionnaireJSON = mapper.createObjectNode();
        questionnaireJSON.put("Date", mqSubmit.getDateSubmitted());
        System.out.println("Hashtable for reference");
        System.out.println(mqSubmit.getAnswers().toString());
        ArrayNode answersNode = mapper.createArrayNode();
        for (String key : mqSubmit.getAnswers().keySet()) {
            System.out.println("Creating node for key: " + key);
            System.out.println("value for key: " + mqSubmit.getAnswers().get(key));
            ObjectNode answerNode = mapper.createObjectNode();
            answerNode.put("QuestionID", key);
            answerNode.put("Answer", mqSubmit.getAnswers().get(key));
//            System.out.println(answerNode.toPrettyString());
            answersNode.addObject().put("answer id=\"" + key + "\"", answerNode);
        }
//        System.out.println("Answers node");
//        System.out.println(answersNode);
        // answersNode.addAll(answersNode);
        questionnaireJSON.put("answers", answersNode);
        return questionnaireJSON.toString();
    }

    private String parseIdFromKey(String key) {
        return "";
    }
}
