
package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.ViewsMentee;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

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
            Each participant (who is a volunteer) stored in Views has an
            Id (in this case, participant id). This test ensures that if they
            don't have one, it returns null
        */
        assertNull(menteeService.extractId(id));
    }

    @DisplayName("should return true when valid views mentee is created")
    @Test
    public void shouldReturnTrueWhenValidViewsMenteeIsCreated() throws JSONException {
        // build
        JSONObject menteeObject = new JSONObject(
                "{" +
                        "\"participant id\":\"3\"," +
                        "\"Forename\":\"Team\", " +
                        "\"Lastname\":\"Mercury\"" +
                   "}"
        );

        ViewsMentee viewsMentee = new ViewsMentee(3, "Team", "Mercury");

        Object id = "participant id=\"3\"";

        // operate
        doReturn(viewsMentee).when(menteeService).buildMentee(menteeObject, id);

        // check
        assertAll(
                () -> assertEquals(viewsMentee, menteeService.buildMentee(menteeObject, id)),
                () -> assertEquals(viewsMentee.getParticipantId(), menteeService.buildMentee(menteeObject, id).getParticipantId()),
                () -> assertEquals(viewsMentee.getFirstName(), menteeService.buildMentee(menteeObject, id).getFirstName()),
                () -> assertEquals(viewsMentee.getLastName(), menteeService.buildMentee(menteeObject, id).getLastName())
        );
    }

    @DisplayName("should return true when mentees are retrieved from views as baytree will have mentees in views")
    @Test
    public void shouldReturnTrueWhenMenteesAreRetrievedFromViewsAsBaytreeWillHaveMenteesInViews() {
        List<Optional<ViewsMentee>> mentees = menteeService.getMenteesFromViews();

        assertNotNull(mentees);
    }
}

