package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;

import com.baytree_mentoring.baytree_mentoring.repositories.MenteeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenteeService {
    private final MenteeRepository menteeRepository;

    public MenteeService(MenteeRepository menteeRepository) {
        this.menteeRepository = menteeRepository;
    }

    public void add(Mentee mentee) {
        menteeRepository.save(mentee);
    }

    public List<Mentee> getAllMentors() {
        return menteeRepository.findAll();
    }

}
