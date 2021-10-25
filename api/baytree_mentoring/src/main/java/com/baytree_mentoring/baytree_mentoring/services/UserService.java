package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getMentorsFromViews(){

    }

    public ArrayList<User> getAllMentorsFromDatabase(){
        return (ArrayList<User>) userRepository.findAll();
    }
}
