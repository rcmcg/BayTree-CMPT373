package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.MentoringRelationship;
import com.baytree_mentoring.baytree_mentoring.repositories.MentoringRelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MentoringRelationshipService {
    private final MentoringRelationshipRepository mentoringRelationshipRepository;

    public MentoringRelationshipService(MentoringRelationshipRepository mentoringRelationshipRepository) {
        this.mentoringRelationshipRepository = mentoringRelationshipRepository;
    }

    public void add(MentoringRelationship mentoringRelationship) {
        mentoringRelationshipRepository.save(mentoringRelationship);
    }

    public Optional<MentoringRelationship> getRelationById(Long id) {
        return mentoringRelationshipRepository.findById(id);
    }

    public List<MentoringRelationship> getAllMentoringRelationshipsByMentorId(Long id) {
        return mentoringRelationshipRepository.findAllById(Collections.singleton(id));
    }

    public boolean isRelationAdded(MentoringRelationship mentoringRelationship) {
        return mentoringRelationshipRepository.existsById(mentoringRelationship.getMentor().getViewsId());
    }
}
