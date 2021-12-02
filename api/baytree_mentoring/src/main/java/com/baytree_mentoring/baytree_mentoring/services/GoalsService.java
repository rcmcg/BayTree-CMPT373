package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Goal;
import com.baytree_mentoring.baytree_mentoring.models.User;
import com.baytree_mentoring.baytree_mentoring.repositories.GoalsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GoalsService {

    private final GoalsRepository goalsRepository;

    public GoalsService(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    public void addGoal(Goal goal) {

        goalsRepository.save(goal);
    }

    public boolean isGoalAdded(Goal goal) {
        return goalsRepository.existsById(goal.getGoalId());
    }

    public List<Goal> getAllGoals() {
        return goalsRepository.findAll();
    }

    public void deleteGoalUsingId(long goalId) {
        boolean exist = goalsRepository.existsById(goalId);
        if(!exist) {
            throw new IllegalStateException("Goal with id " + goalId + " does not exists");
        }
        goalsRepository.deleteById(goalId);
    }

}
