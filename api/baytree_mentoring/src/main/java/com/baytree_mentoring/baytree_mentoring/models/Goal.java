package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
public class Goal {
    @Id
    private long goalId;

    private String username;
    private String description;
    private String state;

    public Goal() {

    }
}

