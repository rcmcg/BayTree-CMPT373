package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MenteeServiceTest {
    private static MenteeService menteeService;

    @BeforeAll
    static void setup() {
        menteeService = mock(MenteeService.class);
    }

    @DisplayName("should return true when mentee is successfully added")
    @Test
    public void shouldReturnTrueWhenMenteeIsSuccessfullyAdded() {
        // build
        Mentee mentee = new Mentee(1L, "test",
                "mentee");

        // operate
        menteeService.add(mentee);
        doReturn(true).when(menteeService).isMenteeAdded(mentee);

        // check
        assertTrue(menteeService.isMenteeAdded(mentee));
    }

    @DisplayName("should return a list of all mentees")
    @Test
    public void shouldReturnAListOfAllMentees() {
        // build
        Mentee mentee = new Mentee();

        // operate
        menteeService.add(mentee);
        doReturn(List.of(mentee)).when(menteeService).getAllMentees();

        // check
        assertAll(
                () -> assertEquals(menteeService.getAllMentees().size(), List.of(mentee).size()),
                () -> assertEquals(menteeService.getAllMentees().get(0), mentee)
        );
    }
}
