package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.models.ViewsSessionGroup;
import com.baytree_mentoring.baytree_mentoring.models.ViewsVolunteeringRole;
import com.baytree_mentoring.baytree_mentoring.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    @PutMapping("/user/mentors/{id}/sessiongroup")
    private String updateMentorSessionGroupIdAndSessionGroupName(@PathVariable String id, @RequestBody ViewsSessionGroup viewsSessionGroup) {
        int mentorId = Integer.parseInt(id);
        long viewsSessionGroupId = viewsSessionGroup.getViewsSessionGroupId();
        String viewsSessionGroupName = viewsSessionGroup.getViewsSessionGroupName();
        try {
            userService.updateMentorSessionGroup(mentorId, viewsSessionGroupId, viewsSessionGroupName);
            return "Successfully updated mentor session group association";
        } catch (Exception e) {
            // todo return an exception
            return "Failed to update mentor";
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @PutMapping("/user/mentors/{id}/volunteeringrole")
    private String updateVolunteeringRoleForMentor(@PathVariable String id, @RequestBody String requestBody) throws Exception {
        int mentorId = Integer.parseInt(id);
        try {
            userService.updateVolunteeringRoleForMentor(mentorId, requestBody);
            return "Successfully updated mentor volunteering role";
        } catch (Exception e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @GetMapping("/user/mentors/{id}/sessiongroup")
    private int getSessionGroupForMentor(@PathVariable String id) throws Exception {
        int mentorId = Integer.parseInt(id);
        try {
            int sessionGroup = userService.getSessionGroupForMentor(mentorId);
            return sessionGroup;
        } catch (Exception e) {
            throw new Exception("Unable to get session group id for mentor " + mentorId);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @GetMapping("/user/mentors/{id}/volunteeringrole")
    private String getVolunteeringRoleForMentor(@PathVariable String id) throws Exception {
        int mentorId = Integer.parseInt(id);
        try {
            String volunteeringRole = userService.getVolunteeringRoleForMentor(mentorId);
            return volunteeringRole;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
