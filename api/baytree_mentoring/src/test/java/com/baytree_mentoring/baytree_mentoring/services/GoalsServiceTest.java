package com.baytree_mentoring.baytree_mentoring.services;
import com.baytree_mentoring.baytree_mentoring.models.Goal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GoalsServiceTest {
    private static GoalsService goalsService;

    @BeforeAll
    static void setup() {
        goalsService = mock(GoalsService.class);
    }

    @DisplayName("should return true when a goal is successfully added")
    @Test
    public void shouldReturnTrueWhenMentorIsSuccessfullyAdded() {
        // build
        Goal goal = new Goal(1, "testUser", 5, "initial test", "done");

        // operate
        goalsService.addGoal(goal);
        doReturn(true).when(goalsService).addGoal(goal);

        // check
        assertTrue(goalsService.isGoalAdded(goal));
    }

    @DisplayName("should return a list of all goals")
    @Test
    public void shouldReturnAListOfAllGoals() {
        // build
        Goal goal = new Goal();

        // operate
        goalsService.addGoal(goal);
        doReturn(List.of(goal)).when(goalsService).getAllGoals();

        // check
        assertAll(
                () -> assertEquals(goalsService.getAllGoals().size(), List.of(goal).size()),
                () -> assertEquals(goalsService.getAllGoals().get(0), goal)
        );
    }
}

