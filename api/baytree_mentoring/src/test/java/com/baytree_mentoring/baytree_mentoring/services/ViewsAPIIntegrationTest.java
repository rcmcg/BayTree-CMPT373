package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsAPIIntegrationTest {
    private ViewsAPIIntegration viewsAPIIntegration = new ViewsAPIIntegration();
    @Test
    void uploadSessionInformationTest1() {
        // Mercury Mentee2 participantId is 39
        Session ses = new Session(
                39,
                "2021-09-28 20:12:12 -0400",
                "2021-09-28 21:12:12 -0400",
                "Some notes");
        boolean retValue = viewsAPIIntegration.sendCompletedSessionFormToViews(ses);
    }
}