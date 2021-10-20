package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
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
        sessionService = mock(SessionService.class);
    }

    @DisplayName("should return true when session form is complete")
    @Test
    public void shouldReturnTrueWhenSessionFormIsComplete() {
        Session session = new Session(1, "2021-01-01 20:20:20 -0400", "2021-01-01 21:20:20 -0400", "session notes");

        doReturn(true).when(sessionService).isSessionFormComplete(session);

        assertTrue(sessionService.isSessionFormComplete(session));
    }

    @DisplayName("should return true when session is successfully added")
    @Test
    public void shouldReturnTrueWhenSessionIsSuccessfullyAdded() {
        Session session = new Session(1, "2021-01-01 20:20:20 -0400", "2021-01-01 21:20:20 -0400", "session notes");

        sessionService.addSession(session);
        doReturn(true).when(sessionService).isSessionAdded(session);

        assertTrue(sessionService.isSessionAdded(session));
    }

    @DisplayName("should return a list of all sessions")
    @Test
    public void shouldReturnAListOfAllSessions() {
        Session session = new Session(1, "2021-01-01 20:20:20 -0400", "2021-01-01 21:20:20 -0400", "session notes");

        sessionService.addSession(session);
        doReturn(List.of(session)).when(sessionService).getAllSession();

        assertAll(
                () -> assertEquals(sessionService.getAllSession().size(), List.of(session).size()),
                () -> assertEquals(sessionService.getAllSession().get(0), session)
        );
    }
}
