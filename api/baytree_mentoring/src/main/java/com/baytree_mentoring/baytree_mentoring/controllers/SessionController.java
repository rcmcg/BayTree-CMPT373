package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedSessionAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.models.ViewsSessionGroup;
import com.baytree_mentoring.baytree_mentoring.models.ViewsVolunteeringRole;
import com.baytree_mentoring.baytree_mentoring.services.SessionService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {
    private final SessionService sessionService;

    private static final String SUCCESS = "Session Added";

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    // TODO: Make this cross origin config global (for all controllers, not just SessionController)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @PostMapping("/session/add")
    private String addSession(@RequestBody Session ses) {
        boolean uploadSuccess = sessionService.sendCompletedSessionFormToViews(ses);
        if (uploadSuccess) {
            return SUCCESS;
        } else {
            String error = "Failed to upload session to Views database";
            throw new FailedSessionAddingException(error);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sessiongroups/get")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private List<ViewsSessionGroup> getSessionGroupsFromViews() {
        return sessionService.getSessionGroupsFromViews();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sessiongroups/volunteeringroles")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private List<ViewsVolunteeringRole> getVolunteeringRolesFromViews() throws UnirestException {
        return sessionService.getVolunteeringRolesFromViews();
    }
}

