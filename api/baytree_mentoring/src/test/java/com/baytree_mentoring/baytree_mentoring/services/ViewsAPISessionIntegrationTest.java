package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import org.junit.jupiter.api.Test;

class ViewsAPISessionIntegrationTest {
    private ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();
    @Test
    void uploadSessionInformationTest1() {
        // Mercury Mentee2 participantId is 39
        Session ses = new Session(
                39,
                "2021-10-15 20:12:12 -0400",
                "2021-10-15 21:12:12 -0400",
                "Some notes");
        boolean retValue = viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses);
    }
}