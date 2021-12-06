package com.baytree_mentoring.baytree_mentoring.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

@Getter
public class MonthlyQuestionnaireSubmit {
    private int menteeId;
    private String questionnaireMonth;
    private String questionnaireYear;
    private String dateSubmitted;
    private List<String> questionIds = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    public MonthlyQuestionnaireSubmit(int menteeId, String questionnaireMonth, String questionnaireYear, String date, String[] questionIds, String[] answers) {
        this.menteeId = menteeId;
        this.questionnaireMonth = questionnaireMonth;
        this.questionnaireYear = questionnaireYear;
        this.dateSubmitted = date;

        for(int i = 0; i < questionIds.length; i++) {
            this.questionIds.add(questionIds[i]);
            this.answers.add(answers[i]);
        }
    }
}
