package com.baytree_mentoring.baytree_mentoring.repositories;

import com.baytree_mentoring.baytree_mentoring.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalsRepository extends JpaRepository<Goal, Long> {
}
