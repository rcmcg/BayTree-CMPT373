package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.repositories.MonthlyQuestionnaireRepository;
import com.baytree_mentoring.baytree_mentoring.util.ViewsApiQuestionnaireIntegration;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MonthlyQuestionnaireServiceTest {
    private static MonthlyQuestionnaireService monthlyQuestionnaireService;
    private static MonthlyQuestionnaireRepository monthlyQuestionnaireRepository;
    private ViewsApiQuestionnaireIntegration viewsApiQuestionnaireIntegration = new ViewsApiQuestionnaireIntegration();

    @BeforeAll
    static void setup() {
        // monthlyQuestionnaireService = mock(MonthlyQuestionnaireService.class);
        monthlyQuestionnaireRepository = mock(MonthlyQuestionnaireRepository.class);
        monthlyQuestionnaireService = new MonthlyQuestionnaireService(monthlyQuestionnaireRepository);
    }

    @DisplayName("should return true when monthly questionnaire form is successfully added")
    @Disabled
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
    @Disabled
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
    void getMonthlyQuestionnaireFromViews() throws Exception {
        // Get a complete MQ to send back to user-frontend
        // TODO: Get a mock DB working so can test this function
        // assertDoesNotThrow(() -> monthlyQuestionnaireService.getMonthlyQuestionnaireForFrontend(11, 21));
    }

    @Test
    void getMonthlyQuestionnaireViewsId() {
        // Get the views ID for a year-month pair
        // TODO: Get a mock DB working so can test this function
//        monthlyQuestionnaireRepository.add()
//        monthlyQuestionnaireService.getMonthlyQuestionnaireViewsId()
    }
}
