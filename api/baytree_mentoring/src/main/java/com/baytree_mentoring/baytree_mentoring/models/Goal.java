package com.baytree_mentoring.baytree_mentoring.models;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goalId;

    private String username;
    private String description;
    private String state;
}

