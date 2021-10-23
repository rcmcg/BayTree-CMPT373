package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor

public class Mentor {
    @Id
    private int mentorId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String status;

    private String role;

    private String startDate;

    private String endDate;

    private String phoneNumber;

    @NotNull
    private String email;

    private String ethnicity;

    //TODO many to one association
    private List<Mentee> menteeList;

    public Mentor() {

    }
}
