package com.baytree_mentoring.baytree_mentoring.repositories;

import com.baytree_mentoring.baytree_mentoring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.firstName = ?1")
    User findByFirstName(String firstName);
}
