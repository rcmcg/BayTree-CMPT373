package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor

public class Mentee {
    @Id
    private double menteeId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Mentee() {

    }
}
