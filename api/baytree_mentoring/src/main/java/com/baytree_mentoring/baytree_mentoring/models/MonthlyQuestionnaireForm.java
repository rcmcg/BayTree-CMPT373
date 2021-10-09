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
public class MonthlyQuestionnaireForm {
    @Id
    private String mentorName;

    @Id
    private String menteeName;

    @Id
    private String submissionTimeLocal;

    private Instant submissionTimeUTC;

    private int month;

    private int menteeEngagementScore;

    private int menteeArrivalScore;

    public MonthlyQuestionnaireForm(String mentorName, String menteeName, String submissionTimeLocal, int month, int menteeEngagementScore, int menteeArrivalScore) {
        this.mentorName = mentorName;
        this.menteeName = menteeName;
        this.submissionTimeLocal = submissionTimeLocal;
        this.month = month;
        this.menteeEngagementScore = menteeEngagementScore;
        this.menteeArrivalScore = menteeArrivalScore;

        this.submissionTimeUTC = convertToUTC(submissionTimeLocal);
    }

    private Instant convertToUTC(String time) {
        String pattern = "yyyy-MM-dd HH:mm:ss z";
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return localDateTime.atZone(ZoneId.of(time.substring(20))).toInstant();
    }
}
