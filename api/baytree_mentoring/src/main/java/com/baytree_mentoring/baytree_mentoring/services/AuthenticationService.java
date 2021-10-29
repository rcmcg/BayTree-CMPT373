package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Authentication;
import com.baytree_mentoring.baytree_mentoring.repositories.AuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }


    public void addUser(Authentication user) {
        authenticationRepository.save(user);
    }

    public List<Authentication> getAllUsers() {
        return authenticationRepository.findAll();
    }

}
