package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedAddingGoalException;
import com.baytree_mentoring.baytree_mentoring.exceptions.FailedResourceAddingException;
import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Goal;
import com.baytree_mentoring.baytree_mentoring.models.Resource;
import com.baytree_mentoring.baytree_mentoring.services.GoalsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoalsController {

    private final GoalsService goalsService;

    private static final String SUCCESS = "Goal Added";
    private static final String deleteSUCCESS = "Resource deleted";

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/goal/add")
    @CrossOrigin(origins = "http://localhost:3000")
    private String addGoal(@RequestBody Goal gl){
        goalsService.addGoal(gl);

        if (goalsService.isGoalAdded(gl)) {
            return SUCCESS;
        }

        String error = "Failed to add the Goal.";
        throw new FailedAddingGoalException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goal/get/all")
    @CrossOrigin(origins = "http://localhost:3000")
    private List<Goal> getAllGoals() {
        return goalsService.getAllGoals();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/goal/delete/{goalId}")
    @CrossOrigin(origins = "http://localhost:3000")
    private String deleteGoal(@PathVariable("goalId") Long gId){

        List<Goal> goals = goalsService.getAllGoals();

        for(Goal g : goals) {
            if(g.getGoalId() == gId) {
                goalsService.deleteGoalUsingId(gId);
                return deleteSUCCESS;
            }
        }

        String error = "Failed to Delete the Goal.";
        throw new FailedAddingGoalException(error);
    }
}
