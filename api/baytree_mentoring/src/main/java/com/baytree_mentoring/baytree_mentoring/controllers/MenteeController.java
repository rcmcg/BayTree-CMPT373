package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.User;
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
    public List<Mentee> getAllMenteesFromViewsAndUploadDatabase(){
        menteeService.getAllMenteesFromViewsThenUpdateDatabase();
        return menteeService.getAllMenteesFromDatabase();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/mentees/all")
    private List<Mentee> getAllMenteesFromDatabase() {
        return menteeService.getAllMenteesFromDatabase();
    }
}
