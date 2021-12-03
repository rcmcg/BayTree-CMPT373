package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

import javax.xml.bind.JAXB;
import java.io.StringWriter;
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

    public String convertQuestionnaireToXML(MonthlyQuestionnaireSubmit mqSubmit) {
        String xmlString = "<answers>\n";
        xmlString += "\t<EntityType>" + "Person" + "</EntityType>\n";
        xmlString += "\t<EntityId>" + mqSubmit.getMenteeId() + "</EntityId>\n";
        for (String key: mqSubmit.getAnswers().keySet()) {
            xmlString += "\t<answer id=" + "\"" + parseIdFromKey(key) + "\">\n";
            xmlString += "\t\t<QuestionID>" + parseIdFromKey(key) + "</QuestionID>\n";
            xmlString += "\t\t<Answer>" + mqSubmit.getAnswers().get(key) + "</Answer>\n";
            xmlString += "\t</answer>\n";
        }
        xmlString += "</answers>\n";
        System.out.println(xmlString);
        return xmlString;
    }

    public String createQuestionnaireUploadJSON(MonthlyQuestionnaireSubmit mqSubmit) {
        ObjectNode questionnaireJSON = mapper.createObjectNode();
        // questionnaireJSON.put("Date", mqSubmit.getDateSubmitted());
        System.out.println("Hashtable for reference");
        System.out.println(mqSubmit.getAnswers().toString());
        ArrayNode answersNode = mapper.createArrayNode();
        // ObjectNode answersNode = mapper.createObjectNode();
        for (String key : mqSubmit.getAnswers().keySet()) {
            System.out.println("Creating node for key: " + key);
            System.out.println("value for key: " + mqSubmit.getAnswers().get(key));
            ObjectNode answerNode = mapper.createObjectNode();
            answerNode.put("QuestionID", parseIdFromKey(key));
            answerNode.put("Answer", mqSubmit.getAnswers().get(key));
//            System.out.println(answerNode.toPrettyString());
            // answersNode.put("answer id=\"" + parseIdFromKey(key) + "\"", answerNode);
            answersNode.addObject().put("answer id=\"" + parseIdFromKey(key) + "\"", answerNode);
        }
//        System.out.println("Answers node");
//        System.out.println(answersNode);
        // answersNode.addAll(answersNode);
        questionnaireJSON.put("answers", answersNode);
        return questionnaireJSON.toString();
    }

    private String parseIdFromKey(String key) {
        // Key has form "question id=\"54\". Parse the id from it
        System.out.println("parseIdFromKey(): ");
        System.out.println(key);
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
