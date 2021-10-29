package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SessionServiceTest {
    private static SessionService sessionService;

    @BeforeAll
    static void setup() {
        sessionService = new SessionService();
        // TODO: Replace with mock data so don't have to constantly upload to Views database for testing
        // sessionService = mock(SessionService.class);
    }
    // Mercury Mentee2 participantId is 39
    // Mercury Mentor participantId is 42
    // Mercury Test Session Group is 10
    // Mercury Team participantId is 28
    @Test
    void sendCompletedSessionFormToViewsGoodDataShouldPass() {
        Session ses = new Session(
                39,
                42,
                10,
                true,
                true,
                "2021-10-26 20:12:12 -0400",
                "2021-10-26 21:12:12 -0400",
                28,
                "Some notes");
        assertDoesNotThrow(() -> sessionService.sendCompletedSessionFormToViews(ses));
    }

    @Test
    void sendCompletedSessionFormToViewsBadDataShouldReturnFalse() {
        Session ses = new Session(
                -1,
                42,
                10,
                true,
                true,
                "2021-10-26 20:12:12 -0400",
                "2021-10-26 21:12:12 -0400",
                28,
                "Some notes");
        assertFalse(() -> sessionService.sendCompletedSessionFormToViews(ses));
    }
}
