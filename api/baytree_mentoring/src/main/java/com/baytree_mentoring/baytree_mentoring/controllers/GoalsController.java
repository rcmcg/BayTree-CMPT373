package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedAddingGoalException;
import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Goal;
import com.baytree_mentoring.baytree_mentoring.services.GoalsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoalsController {

    private final GoalsService goalsService;

    private static final String SUCCESS = "Goal Added";

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/goal/addGoal")
    @CrossOrigin(origins = "http://localhost:3000")
    private String AddGoal(@RequestBody Goal gl){
        goalsService.addGoal(gl);

        if (goalsService.isGoalAdded(gl)) {
            return SUCCESS;
        }

        String error = "Failed to add the Goal.";
        throw new FailedAddingGoalException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goal/get/allGoal")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<Goal> getAllGoals() {
        return goalsService.getAllGoals();
    }
}
