package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.models.MentoringRelationship;
import com.baytree_mentoring.baytree_mentoring.services.MentoringRelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class MentoringRelationshipController {
    private final MentoringRelationshipService mentoringRelationshipService;

    private static final String SUCCESS = "Mentor-Mentee Relationship Added";

    public MentoringRelationshipController(MentoringRelationshipService mentoringRelationshipService) {
        this.mentoringRelationshipService = mentoringRelationshipService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/relation/add")
    private String AddMentee(@RequestBody User mtr, @RequestBody Mentee mte){
        User mentor = new User(mtr.getViewsId(), mtr.getFirstName(), mtr.getLastName(), mtr.getName(), mtr.getEmail(),
                mtr.getStatus(), mtr.getStartDate(), mtr.getEndDate(), mtr.getPhoneNumber(), mtr.getEthnicity(), mtr.getAddress(), mtr.getRole());
        Mentee mentee = new Mentee(mte.getMenteeId(), mte.getFirstName(),mte.getLastName());

        MentoringRelationship relation = new MentoringRelationship(mentor, mentee);
        mentoringRelationshipService.add(relation);
        if (mentoringRelationshipService.isRelationAdded(relation)) {
            return SUCCESS;
        }

        String error = "Failed to add the Mentee.";
        throw new FailedUserAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/relation/get/{id}")
    private List<MentoringRelationship> getRelationshipById(@PathVariable long id) {
        return mentoringRelationshipService.getAllMentoringRelationshipsByMentorId(id);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/relation/get/{id}")
//    private Optional<MentoringRelationship> getRelationshipById(@PathVariable long id) {
//        return mentoringRelationshipService.getRelationById(id);
//    }
}
