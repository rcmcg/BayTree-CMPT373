package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.ViewsMentee;
import com.baytree_mentoring.baytree_mentoring.services.MenteeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MenteeController {
    private final MenteeService menteeService;

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

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fetchAllMentees")
    private List<Optional<ViewsMentee>> test() {
        return menteeService.getMenteesFromViews();
    }
}
