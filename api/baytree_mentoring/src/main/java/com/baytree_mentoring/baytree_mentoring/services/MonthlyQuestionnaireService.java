package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireForm;
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

    public void add(MonthlyQuestionnaireForm monthlyQuestionnaireForm) {
        monthlyQuestionnaireRepository.save(monthlyQuestionnaireForm);
    }

    public List<MonthlyQuestionnaireForm> getAllMonthlyQuestionnaireForms() {
        return monthlyQuestionnaireRepository.findAll();
    }

    public boolean isMonthlyQuestionnaireAdded(MonthlyQuestionnaireForm monthlyQuestionnaireForm) {
        return monthlyQuestionnaireRepository.existsById(new MonthlyQuestionnaireId(monthlyQuestionnaireForm.getMentorName(), monthlyQuestionnaireForm.getMenteeName(), monthlyQuestionnaireForm.getSubmissionTimeLocal()));
    }
}
