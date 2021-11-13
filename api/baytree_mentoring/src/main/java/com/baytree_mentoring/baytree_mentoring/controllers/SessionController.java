package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedSessionAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.models.ViewsMentee;
import com.baytree_mentoring.baytree_mentoring.services.MenteeService;
import com.baytree_mentoring.baytree_mentoring.services.SessionService;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SessionController {
    private final SessionService sessionService;
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();
    private final MenteeService menteeService;

    private static final String SUCCESS = "Session Added";

    public SessionController(SessionService sessionService, MenteeService menteeService) {
        this.menteeService = menteeService;
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

    // Todo: refactor this method and related changes in this commit
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    private String test() throws UnirestException {
////        System.out.println("inside test()");
//        // return "WHATEVER THE RESPONSE IS";
        String URL = "https://app.viewsapp.net/api/restful/contacts/participants/search?q=";
////        System.out.println("test() before response");
        HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
////        System.out.println("test() after response");
////        System.out.println(response.getBody());
//
//        JSONObject body = new JSONObject(response.getBody());
////        System.out.println("body: " + body);
//        String beginningKey = body.names().getString(0);
////        System.out.println("beginningKey: " + beginningKey);
////        System.out.println();
//
////        System.out.println("NO INDEX: " + body.names());
//
//        JSONObject participants = body.getJSONObject(beginningKey);
////        System.out.println("participants: " + participants);
////        String key = participants.names().getString(0);
////        System.out.println("key: " + key);
////        System.out.println();
//
//        for(Object o : participants.names()) {
//            JSONObject p1 = participants.getJSONObject(o.toString());
//            System.out.println("key: " + o.toString() + ", p1: " + p1.getString("Forename"));;
//        }

        System.out.println(menteeService.getMenteesFromViews().size());

        for(Optional<ViewsMentee> vm : menteeService.getMenteesFromViews()) {
            if(vm.isPresent()) {
                System.out.println(vm.get().getParticipantId());
            }
        }

        return response.getBody();
    }
}

