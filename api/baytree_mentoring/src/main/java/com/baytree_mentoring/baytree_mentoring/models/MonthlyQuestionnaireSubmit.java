package com.baytree_mentoring.baytree_mentoring.models;

import java.util.Hashtable;

public class MonthlyQuestionnaireSubmit {
    private int menteeID;
    private String date;
    Hashtable<String, String> answers = new Hashtable<String, String>();

    public MonthlyQuestionnaireSubmit(int menteeID, String date, Hashtable<String, String> answers) {
        this.menteeID = menteeID;
        this.date = date;
        this.answers = answers;
    }
}
