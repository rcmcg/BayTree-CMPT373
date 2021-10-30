package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor

public class Mentee {
    @Id
    private int menteeId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public Mentee(int menteeId, String firstName, String lastName) {
        this.menteeId = menteeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
