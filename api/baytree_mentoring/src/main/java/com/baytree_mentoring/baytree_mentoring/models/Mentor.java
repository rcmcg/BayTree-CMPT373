package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class Mentor {
    @Id
    private long mentorId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

//    private String status;
//
//    private String role;
//
//    private String startDate;
//
//    private String endDate;
//
//    @NotNull
//    private String email;
//
//    private String phoneNumber;
//
//    private String ethnicity;

    //TODO many to one association
//    @OneToMany
//    @JoinColumn(name = "mentee_id")
//    private List<Mentee> menteeList;

    public Mentor() {

    }
}
