package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedGoalAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Goal;
import com.baytree_mentoring.baytree_mentoring.services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class    GoalController {

    private final GoalService goalService;

    private static final String SUCCESS = "Goal Added";

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/goal/add")
    @CrossOrigin(origins = "http://localhost:3000")
    private String AddGoal(@RequestBody Goal gl){
        Goal goal = new Goal( gl.getGoalId(), gl.getUsername(),gl.getProgressPoints(), gl.getDescription(),gl.getState());

        goalService.addGoal(goal);

        List<Goal> goals = goalService.getAllGoals();

        for(Goal g : goals) {
            if(g.getUsername() == goal.getUsername()) {
                return SUCCESS;
            }
        }
        String error = "Failed to add the User.";
        throw new FailedGoalAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goal/get/all")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }
}
