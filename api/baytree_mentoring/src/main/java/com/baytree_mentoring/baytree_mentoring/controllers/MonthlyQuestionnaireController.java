package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedMonthlyQuestionnaireAddingException;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireForm;
import com.baytree_mentoring.baytree_mentoring.services.MonthlyQuestionnaireService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MonthlyQuestionnaireController {
    private final MonthlyQuestionnaireService monthlyQuestionnaireService;

    private static final String SUCCESS = "MonthlyQuestionnaireForm Added";

    public MonthlyQuestionnaireController(MonthlyQuestionnaireService monthlyQuestionnaireService) {
        this.monthlyQuestionnaireService = monthlyQuestionnaireService;
    }

    // Todo: Make this cross origin config global (for all controllers, not just SessionController)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/monthlyquestionnaire/add")
    private String addMonthlyQuestionnaire(@RequestBody @Valid MonthlyQuestionnaireForm monQueForm) {
        MonthlyQuestionnaireForm monthlyQuestionnaireForm = new MonthlyQuestionnaireForm(monQueForm.getMentorName(), monQueForm.getMenteeName(),
                monQueForm.getSubmissionTimeLocal(), monQueForm.getMonth(), monQueForm.getMenteeEngagementScore(), monQueForm.getMenteeArrivalScore());

        monthlyQuestionnaireService.add(monthlyQuestionnaireForm);

        if(monthlyQuestionnaireService.isMonthlyQuestionnaireAdded(monthlyQuestionnaireForm)) {
            return SUCCESS;
        }

        String error = "Failed to add the MonthlyQuestionnaireForm.";
        throw new FailedMonthlyQuestionnaireAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/monthlyquestionnaire/get/all")
    private List<MonthlyQuestionnaireForm> getAllMonthlyQuestionnaireForms() {
        return monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms();
    }
}
