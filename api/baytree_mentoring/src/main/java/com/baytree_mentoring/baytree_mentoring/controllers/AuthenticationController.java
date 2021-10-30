package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Authentication;
import com.baytree_mentoring.baytree_mentoring.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private static final String SUCCESS = "User Added";

    public AuthenticationController(AuthenticationService authenticationservice, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/add")
    private String AddUser(@RequestBody Authentication usr){
        Authentication user = new Authentication(usr.getUsername(),usr.getPassword());

        authenticationService.addUser(user);

        List<Authentication> users = authenticationService.getAllUsers();

        for(Authentication s : users) {
            if(s.getUsername() == user.getUsername()) {
                return SUCCESS;
            }
        }

        String error = "Failed to add the User.";
        throw new FailedUserAddingException(error);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/get/all")
    private List<Authentication> getAllUsers() {
        return authenticationService.getAllUsers();
    }





}
