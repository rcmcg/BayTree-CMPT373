package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.models.ViewsSessionGroup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    @Test
    void sendCompletedSessionFormToViewsGoodDataShouldPass() {
        // Disabled so that session isn't uploaded from pipeline
        Session ses = new Session(
                39,
                42,
                10,
                true,
                true,
                "2021-10-26 20:12:12 -0400",
                "2021-10-26 21:12:12 -0400",
                "Some notes");
        assertDoesNotThrow(() -> sessionService.sendCompletedSessionFormToViews(ses, null));
    }

    @Disabled
    @Test
    void sendCompletedSessionFormToViewsBadDataShouldReturnFalse() {
        // Disabled so session doesn't attempt to upload from pipeline
        Session ses = new Session(
                -1,
                42,
                10,
                true,
                true,
                "2021-10-26 20:12:12 -0400",
                "2021-10-26 21:12:12 -0400",
                "Some notes");
        assertFalse(() -> sessionService.sendCompletedSessionFormToViews(ses, null));
    }

    @Test
    void getSessionGroupsFromViews() {
        List<ViewsSessionGroup> sessionGroups = sessionService.getSessionGroupsFromViews();
        for (ViewsSessionGroup sessionGroup : sessionGroups) {
            System.out.println(sessionGroup.getViewsSessionGroupId());
            System.out.println(sessionGroup.getViewsSessionGroupName());
        }
    }

    @Test
    void parseIdFromKey() {
        String key = "sessiongroup id=\"16\"";
        String expectedSessionGroupId = "16";
        String id = sessionService.parseIdFromKey(key);
        assertEquals(expectedSessionGroupId, id);
    }
}
