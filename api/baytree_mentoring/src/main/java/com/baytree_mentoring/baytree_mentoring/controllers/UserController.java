package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    private static final String MENTOR_SUCCESS = "Mentor Added";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/add/mentor")
    private String addMentor(@RequestBody User mtr){
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
            String e = "Failed to get users from Views";
            throw new FailedUserAddingException(e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/mentors/all")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private List<User> getAllUsersFromDatabase() {
        return userService.getAllMentorsFromDatabase();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/mentors/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private Optional<User> getUserById (@PathVariable String id) {
        return userService.getMentorById(Long.parseLong(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @PutMapping("/user/update/mentors/{id}/sessiongroupid/{sid}")
    private String updateMentorSessionGroupIdAndSessionGroupName(@PathVariable String mentorId, @PathVariable int sessionGroupId) {
        System.out.println("updateMentorSessionGroupIdAndSessionGroupName(): mentorId: " + mentorId);
        System.out.println("updateMentorSessionGroupIdAndSessionGroupName(): sessionGroupId: " + sessionGroupId);
        return "";
    }

}
