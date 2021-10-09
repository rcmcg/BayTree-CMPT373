package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedSessionAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.services.SessionService;
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
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/session/add")
    private String addSession(@RequestBody Session ses) {
        System.out.println(ses.getClockInTimeLocal());
        System.out.println(ses.getClockOutTimeLocal());
        if (sessionService.isSessionFormComplete(ses)) {
            // Send straight to Views
            System.out.println("SessionController: Session form sent to backend is complete");
            System.out.println("Sending form straight to views, don't add it to the database");

            sessionService.sendCompletedSessionFormToViews(ses);

            return SUCCESS;
        } else {
            // Session form is incomplete (missing clock out time), need to save in database until completed
            Session session = new Session(ses.getMenteeId(), ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), ses.getSessionNotes());
            sessionService.addSession(session);

            List<Session> sessions = sessionService.getAllSession();

            for(Session s : sessions) {
                if(s.getMentoringSessionId() == session.getMentoringSessionId()) {
                    return SUCCESS;
                }
            }
        }

        String error = "Failed to add the Session.";
        throw new FailedSessionAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/session/get/all")
    private List<Session> getAllSession() {
        return sessionService.getAllSession();
    }
}

