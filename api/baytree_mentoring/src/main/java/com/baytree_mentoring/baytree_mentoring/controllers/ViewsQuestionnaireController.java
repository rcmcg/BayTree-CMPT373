package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.models.ViewsQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.services.ViewsQuestionnaireService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewsQuestionnaireController {

    private final ViewsQuestionnaireService viewsQuestionnaireService;

    public ViewsQuestionnaireController(ViewsQuestionnaireService viewsQuestionnaireService) {
        this.viewsQuestionnaireService = viewsQuestionnaireService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/questionnaires/get/views/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<ViewsQuestionnaire> getQuestionnairesFromViews(@PathVariable String id) {
        try {
            long mentorId = Long.parseLong(id);
            return viewsQuestionnaireService.getQuestionnairesByMentorId(mentorId);
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/questionnaires/get/views/{id}/size")
    @CrossOrigin(origins = "http://localhost:3000")
    private int getNumberOfQuestionnaires(@PathVariable String id) {
        try {
            long mentorId = Long.parseLong(id);
            return viewsQuestionnaireService.getNumberOfQuestionnairesByMentorId(mentorId);
        } catch (UnirestException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
