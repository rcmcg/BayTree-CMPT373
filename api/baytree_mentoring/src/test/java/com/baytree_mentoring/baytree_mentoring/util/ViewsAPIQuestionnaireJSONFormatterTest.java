package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsAPIQuestionnaireJSONFormatterTest {
    ViewsApiQuestionnaireIntegration viewsApiQuestionnaireIntegration = new ViewsApiQuestionnaireIntegration();
    ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter = new ViewsAPIQuestionnaireJSONFormatter();

    @Test
    @Disabled
    void formatViewsQuestionnaireForFrontend() throws UnirestException {
        HttpResponse<JsonNode> questionnaireFromViews = viewsApiQuestionnaireIntegration.getMonthlyQuestionnaireFromViews(21);
    }

    @Test
    @Disabled
    void createQuestionnaireUploadXML() {
        MonthlyQuestionnaireSubmit mqSubmit = new MonthlyQuestionnaireSubmit(
                4,
                "12",
                "21",
                "2021-11-12T17:14:00",
                new String[]{"question id=\"63\"", "question id=\"9\"", "question id=\"101\"", "question id=\"66\""},
                new String[]{"1", "2", "3", "4"}
        );
        String correctXML = "<answers>\n" +
                                "\t<EntityType>Person</EntityType>\n" +
                                "\t<EntityID>4</EntityID>\n" +
                                "\t<answer id=\"63\"\n" +
                                    "\t\t<QuestionID>63</QuestionID>\n" +
                                    "\t\t<Answer>1</Answer>\n" +
                                "\t</answer>\n" +
                                "\t<answer id=\"9\"\n" +
                                    "\t\t<QuestionID>9</QuestionID>\n" +
                                    "\t\t<Answer>2</Answer>\n" +
                                "\t</answer>\n" +
                                "\t<answer id=\"101\"\n" +
                                    "\t\t<QuestionID>101</QuestionID>\n" +
                                    "\t\t<Answer>3</Answer>\n" +
                                "\t</answer>\n" +
                                "\t<answer id=\"66\"\n" +
                                    "\t\t<QuestionID>66</QuestionID>\n" +
                                    "\t\t<Answer>4</Answer>\n" +
                                "\t</answer>\n" +
                            "</answers>\n";
        String uploadXML = viewsAPIQuestionnaireJSONFormatter.convertQuestionnaireToXML(mqSubmit);
        assertEquals(correctXML, uploadXML);
    }
}