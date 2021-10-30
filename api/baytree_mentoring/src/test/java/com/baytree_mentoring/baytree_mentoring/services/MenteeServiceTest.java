//package com.baytree_mentoring.baytree_mentoring.services;
//
//import com.baytree_mentoring.baytree_mentoring.models.Mentee;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//
//public class MenteeServiceTest {
//    private static MenteeService menteeService;
//
//    @BeforeAll
//    static void setup() {
//        menteeService = mock(MenteeService.class);
//    }
//
//    @DisplayName("should return a list of all mentees")
//    @Test
//    public void shouldReturnAListOfAllMentees() {
//        // build
//        Mentee mentee = new Mentee();
//
//        // operate
//        menteeService.add(mentee);
//        doReturn(List.of(mentee)).when(menteeService).getAllMenteesFromDatabase();
//
//        // check
//        assertAll(
//                () -> assertEquals(menteeService.getAllMenteesFromDatabase().size(), List.of(mentee).size()),
//                () -> assertEquals(menteeService.getAllMenteesFromDatabase().get(0), mentee)
//        );
//    }
//}
