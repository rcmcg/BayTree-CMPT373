
package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.ViewsMentee;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        doReturn(List.of(mentee)).when(menteeService).getAllMenteesFromDatabase();

        // check
        assertAll(
                () -> assertEquals(menteeService.getAllMenteesFromDatabase().size(), List.of(mentee).size()),
                () -> assertEquals(menteeService.getAllMenteesFromDatabase().get(0), mentee)
        );
    }

    @DisplayName("should return true when string id can be converted to long")
    @Test
    public void shouldReturnTrueWhenStringIdCanBeConvertedToLong() {
        String id = "participant id=\"3\"";

        doReturn("3").when(menteeService).extractId(id);
        id = menteeService.extractId(id);

        assertEquals(3, Integer.parseInt(id));
    }

    @DisplayName("should return null when string id is empty")
    @Test
    public void shouldReturnNullWhenStringIdIsEmpty() {
        String id = "participant id=\"\"";

        /*
            This case likely won't happen since each participant (who is a
            volunteer) will have an Id (in this case, participant id) assigned
            to them by Views itself
         */
        assertNull(menteeService.extractId(id));
    }

    @DisplayName("should return true when valid views mentee is created")
    @Test
    public void shouldReturnTrueWhenValidViewsMenteeIsCreated() throws JSONException {
        JSONObject menteeObject = new JSONObject(
                "{" +
                        "\"participant id\":\"3\"," +
                        "\"Forename\":\"Team\", " +
                        "\"Lastname\":\"Mercury\"" +
                   "}"
        );
        ViewsMentee viewsMentee = new ViewsMentee(3, "Team", "Mercury");
        Object id = "participant id=\"3\"";

        doReturn(viewsMentee).when(menteeService).buildMentee(menteeObject, id);

        assertEquals(viewsMentee, menteeService.buildMentee(menteeObject, id));
    }
}

