package com.baytree_mentoring.baytree_mentoring.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(MonthlyQuestionnaireId.class)
public class MonthlyQuestionnaire {
    @Id
    private int month;
    @Id
    private int year;

    private int viewsQuestionnaireId;

    public MonthlyQuestionnaire(int month, int year, int viewsQuestionnaireId) {
        this.month = month;
        this.year = year;
        this.viewsQuestionnaireId = viewsQuestionnaireId;
    }
}

