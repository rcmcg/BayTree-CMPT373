package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.baytree_mentoring.baytree_mentoring.repositories.MonthlyQuestionnaireRepository;
import com.baytree_mentoring.baytree_mentoring.util.ViewsApiQuestionnaireIntegration;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    public String getMonthlyQuestionnaireForFrontend(int year, int month) throws Exception {
        int mqViewsId = getMonthlyQuestionnaireViewsId(year, month);
        String questionnaire = viewsApiQuestionnaireIntegration.getMonthlyQuestionnaire(mqViewsId);
        return questionnaire;
    }

    public int getMonthlyQuestionnaireViewsId(int year, int month) throws Exception {
        MonthlyQuestionnaireId monthlyQuestionnaireId = new MonthlyQuestionnaireId(year, month);
        Optional<MonthlyQuestionnaire> monthlyQuestionnaire =
                monthlyQuestionnaireRepository.findById(monthlyQuestionnaireId);
        if (monthlyQuestionnaire.isPresent()) {
            return monthlyQuestionnaire.get().getViewsQuestionnaireId();
        } else {
            throw new Exception("Questionnaire not found in database");
        }
    }

    public void submitMonthlyQuestionnaireToViews(MonthlyQuestionnaireSubmit mqSubmit) throws Exception {
        try {
            int monthlyQuestionnaireId = getMonthlyQuestionnaireViewsId(Integer.parseInt(mqSubmit.getQuestionnaireYear()), Integer.parseInt(mqSubmit.getQuestionnaireMonth()));
            viewsApiQuestionnaireIntegration.sendCompletedQuestionnaireToViews(mqSubmit, monthlyQuestionnaireId);
        } catch (UnirestException | ParseException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
