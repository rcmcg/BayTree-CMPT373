package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.repositories.MonthlyQuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyQuestionnaireService {
    private final MonthlyQuestionnaireRepository monthlyQuestionnaireRepository;

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
}
