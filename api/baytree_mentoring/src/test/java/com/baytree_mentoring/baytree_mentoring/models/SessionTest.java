package com.baytree_mentoring.baytree_mentoring.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void getClockInTimeUTCString() {
        Session ses = new Session(
                39,
                42,
                10,
                true,
                true,
                "2021-09-28 20:12:12 -0800",
                "2021-09-28 21:12:12 -0800", 42, "Notes"
        );
        assertEquals("2021-09-29 04:12:12", ses.getClockInTimeUTCString());
    }
}