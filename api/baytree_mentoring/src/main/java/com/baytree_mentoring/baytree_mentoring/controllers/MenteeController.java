package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.services.MenteeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenteeController {
    private final MenteeService menteeService;

    private static final String SUCCESS = "Mentee Added";

    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mentee/add")
    private String AddMentee(@RequestBody Mentee mte){
        Mentee mentee = new Mentee(mte.getMenteeId(), mte.getFirstName(),mte.getLastName());
        menteeService.add(mentee);

        if (menteeService.isMenteeAdded(mentee)) {
            return SUCCESS;
        }

        String error = "Failed to add the Mentee.";
        throw new FailedUserAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mentee/get/all")
    private List<Mentee> getAllMentees() {
        return menteeService.getAllMentees();
    }
}
