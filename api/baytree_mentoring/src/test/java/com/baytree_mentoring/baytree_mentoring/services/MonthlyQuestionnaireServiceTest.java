package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MonthlyQuestionnaireServiceTest {
    private static MonthlyQuestionnaireService monthlyQuestionnaireService;

    @BeforeAll
    static void setup() {
        monthlyQuestionnaireService = mock(MonthlyQuestionnaireService.class);
    }

    @DisplayName("should return true when monthly questionnaire form is successfully added")
    @Test
    public void shouldReturnTrueWhenMonthlyQuestionnaireFormIsSuccessfullyAdded() {
        // build
        MonthlyQuestionnaireForm monthlyQuestionnaireForm = new MonthlyQuestionnaireForm("mentor", "mentee",
                "2021-09-08 20:12:12 America/Vancouver", 9, 2, 2);

        // operate
        monthlyQuestionnaireService.add(monthlyQuestionnaireForm);
        doReturn(true).when(monthlyQuestionnaireService).isMonthlyQuestionnaireAdded(monthlyQuestionnaireForm);

        // check
        assertTrue(monthlyQuestionnaireService.isMonthlyQuestionnaireAdded(monthlyQuestionnaireForm));
    }

    @DisplayName("should return a list of all monthly questionnaire forms")
    @Test
    public void shouldReturnAListOfAllMonthlyQuestionnaireForms() {
        // build
        MonthlyQuestionnaireForm monthlyQuestionnaireForm = new MonthlyQuestionnaireForm("mentor", "mentee",
                "2021-09-08 20:12:12 America/Vancouver", 9, 2, 2);

        // operate
        monthlyQuestionnaireService.add(monthlyQuestionnaireForm);
        doReturn(List.of(monthlyQuestionnaireForm)).when(monthlyQuestionnaireService).getAllMonthlyQuestionnaireForms();

        // check
        assertAll(
                () -> assertEquals(monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms().size(), List.of(monthlyQuestionnaireForm).size()),
                () -> assertEquals(monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms().get(0), monthlyQuestionnaireForm)
        );
    }
}
