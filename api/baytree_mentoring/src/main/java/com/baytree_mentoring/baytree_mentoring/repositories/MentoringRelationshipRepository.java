package com.baytree_mentoring.baytree_mentoring.repositories;

import com.baytree_mentoring.baytree_mentoring.models.MentoringRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoringRelationshipRepository extends JpaRepository<MentoringRelationship, Long> {
}
