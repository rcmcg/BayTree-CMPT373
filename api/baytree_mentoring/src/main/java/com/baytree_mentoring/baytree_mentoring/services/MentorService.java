package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentor;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireForm;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireId;
import com.baytree_mentoring.baytree_mentoring.repositories.MentorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public void add(Mentor mentor) {
        mentorRepository.save(mentor);
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public boolean isMentorAdded(Mentor mentor) {
        return mentorRepository.existsById(mentor.getMentorId());
    }
}
