package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.repositories.MonthlyQuestionnaireRepository;
import com.baytree_mentoring.baytree_mentoring.util.ViewsApiQuestionnaireIntegration;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonthlyQuestionnaireService {
    private final MonthlyQuestionnaireRepository monthlyQuestionnaireRepository;
    private final ViewsApiQuestionnaireIntegration viewsApiQuestionnaireIntegration =
            new ViewsApiQuestionnaireIntegration();

    public MonthlyQuestionnaireService(MonthlyQuestionnaireRepository monthlyQuestionnaireRepository) {
        this.monthlyQuestionnaireRepository = monthlyQuestionnaireRepository;
    }

    public void add(MonthlyQuestionnaire monthlyQuestionnaire) {
        monthlyQuestionnaireRepository.save(monthlyQuestionnaire);
    }

    public List<MonthlyQuestionnaire> getAllMonthlyQuestionnaireForms() {
        return monthlyQuestionnaireRepository.findAll();
    }

    public boolean isMonthlyQuestionnaireAdded(MonthlyQuestionnaire monthlyQuestionnaire) {
//        return monthlyQuestionnaireRepository.existsById(new MonthlyQuestionnaireId(
//                monthlyQuestionnaire.getMonth(),
//                monthlyQuestionnaire.getYear(),
//                monthlyQuestionnaire.getViewsQuestionnaireId()));
        // TODO: Implement this function
        return true;
    }

    public String getFormattedMonthlyQuestionnaireForFrontend(int year, int month) throws UnirestException {
        int mqViewsId = getMonthlyQuestionnaireViewsId(year, month);
        String questionnaire = viewsApiQuestionnaireIntegration.getFormattedQuestionnaire(mqViewsId);
        return questionnaire;
    }

    public int getMonthlyQuestionnaireViewsId(int year, int month) {
        MonthlyQuestionnaireId monthlyQuestionnaireId = new MonthlyQuestionnaireId(year, month);
        Optional<MonthlyQuestionnaire> monthlyQuestionnaire =
                monthlyQuestionnaireRepository.findById(monthlyQuestionnaireId);
        if (monthlyQuestionnaire.isPresent()) {
            return monthlyQuestionnaire.get().getViewsQuestionnaireId();
        }
        return -1;
    }
}
