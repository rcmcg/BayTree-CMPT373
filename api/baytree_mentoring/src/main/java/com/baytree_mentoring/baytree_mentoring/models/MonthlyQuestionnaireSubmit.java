package com.baytree_mentoring.baytree_mentoring.models;

import java.util.Hashtable;

public class MonthlyQuestionnaireSubmit {
    private int menteeID;
    private String questionnaireMonth;
    private String questionnaireYear;
    private String dateSubmitted;
    Hashtable<String, String> answers = new Hashtable<String, String>();

    public MonthlyQuestionnaireSubmit(int menteeID, String questionnaireMonth, String questionnaireYear, String date, Hashtable<String, String> answers) {
        this.menteeID = menteeID;
        this.questionnaireMonth = questionnaireMonth;
        this.questionnaireYear = questionnaireYear;
        this.dateSubmitted = date;
        this.answers = answers;
    }
}
