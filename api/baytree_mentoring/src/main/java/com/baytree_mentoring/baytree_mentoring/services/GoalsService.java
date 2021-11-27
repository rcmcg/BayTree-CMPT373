package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Goal;
import com.baytree_mentoring.baytree_mentoring.repositories.GoalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    public void deleteGoalUsingId(long gId) {
        boolean exists = goalsRepository.existsById(gId);

        if(!exists) {
            throw new IllegalStateException("goal with id " + gId + " does not exists");
        }

        goalsRepository.deleteById(gId);
    }

}
