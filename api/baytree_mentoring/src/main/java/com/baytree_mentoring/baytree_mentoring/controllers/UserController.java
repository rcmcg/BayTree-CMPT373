package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private static final String MENTOR_SUCCESS = "Mentor Added";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/add/mentor")
    private String AddMentee(@RequestBody User mtr){
        User mentor = new User(mtr.getViewsId(), mtr.getFirstName(),mtr.getLastName(), mtr.getEmail(),
                mtr.getStatus(), mtr.getStartDate(), mtr.getEndDate(), mtr.getPhoneNumber(), mtr.getEthnicity(), mtr.getAddress(), mtr.getRole());
        userService.addMentorToDatabase(mtr);

        if (userService.isMentorAdded(mtr)) {
            return MENTOR_SUCCESS;
        }

        String error = "Failed to add the Mentor.";
        throw new FailedUserAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/views/mentors")
    public List<User> getMentorsFromViews(){
        boolean uploadSuccess = userService.getAllMentorsFromViewsThenUpdateDatabase();
        if(uploadSuccess) {
            return userService.getAllMentorsFromDatabase();
        }
        else {
            String e = "Failed to get users from Views and/or update database";
            throw new FailedUserAddingException(e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/mentors/all")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<User> getAllUsersFromDatabase() {
        return userService.getAllMentorsFromDatabase();
    }
}
