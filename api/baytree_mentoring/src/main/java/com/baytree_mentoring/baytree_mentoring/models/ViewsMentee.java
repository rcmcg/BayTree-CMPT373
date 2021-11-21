package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;

@AllArgsConstructor
@Getter
public class ViewsMentee {
    @Id
    private final long participantId;

    private final String firstName;

    private final String lastName;
}
