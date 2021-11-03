package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/views/mentors")
    public List<User> getAllUsers(){
        userService.getAllMentorsFromViewsThenUpdateDatabase();
        return userService.getAllMentorsFromDatabase();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/mentors/all")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<User> getAllUsersFromDatabase() {
        return userService.getAllMentorsFromDatabase();
    }
}
