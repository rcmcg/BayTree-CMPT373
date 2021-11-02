package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
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
        MonthlyQuestionnaire monthlyQuestionnaire = new MonthlyQuestionnaire(12, 2021, 18);

        // operate
        monthlyQuestionnaireService.add(monthlyQuestionnaire);
        doReturn(true).when(monthlyQuestionnaireService).isMonthlyQuestionnaireAdded(monthlyQuestionnaire);

        // check
        assertTrue(monthlyQuestionnaireService.isMonthlyQuestionnaireAdded(monthlyQuestionnaire));
    }

    @DisplayName("should return a list of all monthly questionnaire forms")
    @Test
    public void shouldReturnAListOfAllMonthlyQuestionnaireForms() {
        // build
        MonthlyQuestionnaire monthlyQuestionnaire = new MonthlyQuestionnaire(12, 2021, 18);

        // operate
        monthlyQuestionnaireService.add(monthlyQuestionnaire);
        doReturn(List.of(monthlyQuestionnaire)).when(monthlyQuestionnaireService).getAllMonthlyQuestionnaireForms();

        // check
        assertAll(
                () -> assertEquals(monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms().size(), List.of(monthlyQuestionnaire).size()),
                () -> assertEquals(monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms().get(0), monthlyQuestionnaire)
        );
    }
}
