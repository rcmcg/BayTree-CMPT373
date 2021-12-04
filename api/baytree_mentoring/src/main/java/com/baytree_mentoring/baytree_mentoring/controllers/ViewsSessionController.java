package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.models.ViewsSession;
import com.baytree_mentoring.baytree_mentoring.services.ViewsSessionService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewsSessionController {

    private final ViewsSessionService viewsSessionService;

    public ViewsSessionController(ViewsSessionService viewsSessionService) {
        this.viewsSessionService = viewsSessionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sessions/get/views/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private List<ViewsSession> getSessionsFromViews(@PathVariable String id) {
        try {
            long mentorId = Long.parseLong(id);
            return viewsSessionService.getSessionsByMentorId(mentorId);
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }
}
