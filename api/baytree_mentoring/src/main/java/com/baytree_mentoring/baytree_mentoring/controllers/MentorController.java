package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Mentor;
import com.baytree_mentoring.baytree_mentoring.services.MentorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MentorController {

    private final MentorService mentorService;

    private static final String SUCCESS = "User Added";

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mentor/add")
    private String AddMentor(@RequestBody Mentor mtr){
//        Mentor mentor = new Mentor(mtr.getMentorId(),mtr.getFirstName(), mtr.getLastName(), mtr.getStatus(),
//                mtr.getRole(), mtr.getStartDate(), mtr.getEndDate(), mtr.getEmail(), mtr.getPhoneNumber(), mtr.getEthnicity());
        Mentor mentor = new Mentor();
        mentorService.add(mentor);

        List<Mentor> mentors = mentorService.getAllMentors();

        for(Mentor m : mentors) {
            if(m.getMentorId() == m.getMentorId()) {
                return SUCCESS;
            }
        }

        String error = "Failed to add the Mentor.";
        throw new FailedUserAddingException(error);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mentor/get/all")
    private List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mentor/get/{id}")
    private Optional<Mentor> getMentorById(@RequestBody long id) {
        return mentorService.getMentorById(id);
    }
}
