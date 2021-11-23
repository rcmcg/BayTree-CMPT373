package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.util.ViewsAPISessionIntegration;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ViewsAPISessionIntegrationTest {
    private ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();

    @Disabled
    @Test
    void uploadSessionInformationTest() {
        // Mercury Mentee2 participantId is 39
        // Mercury Mentor participantId is 42
        // Mercury Test Session Group is 10
        // Mercury Team participantId is 28
        Session ses = new Session(
                39,
                42,
                10,
                true,
                true,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                28,
                "Some notes");
        assertDoesNotThrow(() -> viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses));
    }

    @Disabled
    @Test
    void uploadSessionWhereMenteeIdDoesNotExist() {
        Session ses = new Session(
                -1,
                42,
                10,
                true,
                true,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                28,
                "Some notes");
        assertThrows(UnirestException.class, () -> viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses));
    }

    @Disabled
    @Test
    void uploadSessionWhereSessionGroupIdDoesNotExist() {
        Session ses = new Session(
                39,
                42,
                -1,
                true,
                true,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                28,
                "Some notes");
        assertThrows(UnirestException.class, () -> viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses));
    }

    @Test
    void uploadSessionWhereMentorDidNotAttend() {
        Session ses = new Session(
                39,
                42,
                10,
                true,
                false,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                42,
                "Mentor did not attend, but both mentee and mentor should be marked as not attended in Views");
        assertDoesNotThrow(() -> viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses));
    }

    @Test
    void uploadSessionWhereMenteeDidNotAttend() {
        Session ses = new Session(
                39,
                42,
                10,
                false,
                true,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                42,
                "Mentee did not attend, but both mentee and mentor should be marked as not attended in Views");
        assertDoesNotThrow(() -> viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses));
    }
}