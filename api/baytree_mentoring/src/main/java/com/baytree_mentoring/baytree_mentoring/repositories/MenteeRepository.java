package com.baytree_mentoring.baytree_mentoring.repositories;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Integer>{
}
