package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MentorServiceTest {
    private static MentorService mentorService;

    @BeforeAll
    static void setup() {
        mentorService = mock(MentorService.class);
    }

    @DisplayName("should return true when mentor is successfully added")
    @Test
    public void shouldReturnTrueWhenMentorIsSuccessfullyAdded() {
        // build
        Mentor mentor = new Mentor();

        // operate
        mentorService.add(mentor);
        doReturn(true).when(mentorService).isMentorAdded(mentor);

        // check
        assertTrue(mentorService.isMentorAdded(mentor));
    }

    @DisplayName("should return a list of all mentors")
    @Test
    public void shouldReturnAListOfAllMentors() {
        // build
        Mentor mentor = new Mentor();

        // operate
        mentorService.add(mentor);
        doReturn(List.of(mentor)).when(mentorService).getAllMentors();

        // check
        assertAll(
                () -> assertEquals(mentorService.getAllMentors().size(), List.of(mentor).size()),
                () -> assertEquals(mentorService.getAllMentors().get(0), mentor)
        );
    }
}
