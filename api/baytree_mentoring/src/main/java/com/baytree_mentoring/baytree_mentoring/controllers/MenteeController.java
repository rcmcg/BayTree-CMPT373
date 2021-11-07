package com.baytree_mentoring.baytree_mentoring.controllers;

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


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/views/mentees")
    public List<Mentee> getAllMenteesFromViewsAndUploadDatabase() {
        menteeService.getAllMenteesFromViewsThenUpdateDatabase();
        return menteeService.getAllMenteesFromDatabase();
    }
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/mentee/add")
//    private String AddMentee(@RequestBody Mentee mte){
//        menteeService.add(mte);
//
//        if (menteeService.isMenteeAdded(mte)) {
//            return SUCCESS;
//        }
//
//        String error = "Failed to add the Mentee.";
//        throw new FailedUserAddingException(error);
//
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/mentees/all")
    private List<Mentee> getAllMenteesFromDatabase() {
        return menteeService.getAllMenteesFromDatabase();
    }
}
