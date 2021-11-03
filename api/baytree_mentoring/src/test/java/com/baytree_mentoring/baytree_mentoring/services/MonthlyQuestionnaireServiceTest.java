package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.repositories.MonthlyQuestionnaireRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MonthlyQuestionnaireServiceTest {
    private static MonthlyQuestionnaireService monthlyQuestionnaireService;
    private static MonthlyQuestionnaireRepository monthlyQuestionnaireRepository;

    @BeforeAll
    static void setup() {
        monthlyQuestionnaireService = mock(MonthlyQuestionnaireService.class);
        monthlyQuestionnaireRepository = mock(MonthlyQuestionnaireRepository.class);
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

    @Test
    public void returnAllMonthlyQuestionnairesTest() {
        List<MonthlyQuestionnaire> mqs = monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms();
        getMonthlyQuestionnaireFromViewsTest();
        System.out.println(mqs);
    }

    @Test
    public void getMonthlyQuestionnaireFromViewsTest() {
        // Should return a fully formed JSON string which contains all the necessary information for
        // the user-frontend to build the questionnaire form so the mentor can answer it
    }

    @Test
    public void getMonthlyQuestionnaireViewsIdTest() {
        int mqViewsId = monthlyQuestionnaireService.getMonthlyQuestionnaireViewsId(11, 2021);
        assertEquals(2, mqViewsId);
    }

    @Test
    void getMonthlyQuestionnaireFromViews() {
        // Get a complete MQ to send back to user-frontend
    }

    @Test
    void getMonthlyQuestionnaireViewsId() {
        // Get the views ID for a year-month pair
//        monthlyQuestionnaireRepository.add()
//        monthlyQuestionnaireService.getMonthlyQuestionnaireViewsId()
    }
}
