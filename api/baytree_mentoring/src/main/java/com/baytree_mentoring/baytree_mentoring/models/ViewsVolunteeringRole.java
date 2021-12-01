package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ViewsVolunteeringRole {
    private final String volunteeringRoleName;

    public ViewsVolunteeringRole(String volunteeringRoleName) {
        System.out.println("ViewsVolunteeringRole constructor");
        System.out.println("volunteeringRoleName: " + volunteeringRoleName);
        this.volunteeringRoleName = volunteeringRoleName;
    }
}
