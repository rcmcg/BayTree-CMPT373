package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ResourceServiceTest {
    private static ResourceService resourceService;

    @BeforeAll
    static void setup() {
        resourceService = mock(ResourceService.class);
    }

    @DisplayName("should return true when Resource is successfully added")
    @Test
    public void shouldReturnTrueWhenResourceIsSuccessfullyAdded() {
        // build
        Resource res = new Resource(1L, "testing",
                "http://resource.com");

        // operate
        resourceService.add(res);
        doReturn(true).when(resourceService).isResourceAdded(res);

        // check
        assertTrue(resourceService.isResourceAdded(res));
    }

    @DisplayName("should return a list of all mentees")
    @Test
    public void shouldReturnAListOfAllResources() {
        // build
        Resource res = new Resource();

        // operate
        resourceService.add(res);
        doReturn(List.of(res)).when(resourceService).getAllResources();

        // check
        assertAll(
                () -> assertEquals(resourceService.getAllResources().size(), List.of(res).size()),
                () -> assertEquals(resourceService.getAllResources().get(0), res)
        );
    }

}
