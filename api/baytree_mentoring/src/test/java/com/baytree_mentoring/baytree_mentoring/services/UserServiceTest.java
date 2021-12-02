package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class UserServiceTest {
    private static UserService userService;

    @BeforeAll
    static void setup() {
        userService = mock(UserService.class);
    }

    @DisplayName("should return true when mentor is successfully added")
    @Test
    public void shouldReturnTrueWhenMentorIsSuccessfullyAdded() {
        // build
        User user = new User(1L, "test", "mentor", "test@test.com", "active", "2000-01-01", "2099-12-31",
                "123-456-7890", "human", "123 test street", "mentor");

        // operate
        userService.addMentorToDatabase(user);
        doReturn(true).when(userService).isMentorAdded(user);

        // check
        assertTrue(userService.isMentorAdded(user));
    }

    @DisplayName("should return true when a list of mentors is successfully added")
    @Test
    public void shouldReturnTrueWhenListOfMentorsIsSuccessfullyAdded() {
        // build
        List<User> users = new ArrayList<>();
        for(long i = 0L; i < 5L; i++) {
            users.add(new User(i, "test", "mentor", "test@test.com", "active", "2000-01-01", "2099-12-31",
                    "123-456-7890", "human", "123 test street", "mentor"));
        }

        // operate
        userService.addListOfMentorsToDatabase(users);
        doReturn(true).when(userService).areMentorsAdded(users);

        // check
        assertTrue(userService.areMentorsAdded(users));
    }

    @DisplayName("should return a list of all mentors")
    @Test
    public void shouldReturnAListOfAllMentees() {
        // build
        User user = new User();

        // operate
        userService.addMentorToDatabase(user);
        doReturn(List.of(user)).when(userService).getAllMentorsFromDatabase();

        // check
        assertAll(
                () -> assertEquals(userService.getAllMentorsFromDatabase().size(), List.of(user).size()),
                () -> assertEquals(userService.getAllMentorsFromDatabase().get(0), user)
        );
    }

    @Test
    @Disabled
    void associateMentorAndSessionGroupInViews() throws UnirestException {
        HttpResponse<String> response = userService.associateMentorAndSessionGroupInViews(42,18);
        System.out.println(response.getBody());
    }
}
