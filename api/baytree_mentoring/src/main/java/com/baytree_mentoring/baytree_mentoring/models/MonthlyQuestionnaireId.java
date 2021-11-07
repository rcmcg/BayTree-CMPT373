package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class MonthlyQuestionnaireId implements Serializable {
    private int year;
    private int month;
}
