package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private long viewsId;

    String firstName;

    String lastName;

    String email;

    String status;

    String startDate;

    String endDate;

    String phoneNumber;

    String ethnicity;

    String address;

    String role;

    @Nullable
    int sessionGroupId;

    @Nullable
    String sessionGroupName;

    @Nullable
    String volunteeringRoleName;

    public User(long viewsId, String firstName, String lastName, String email, String status, String startDate,
                String endDate, String phoneNumber, String ethnicity, String address, String role) {
        this.viewsId = viewsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
        this.ethnicity = ethnicity;
        this.address = address;
        this.role = role;
        this.sessionGroupId = -1;       // Admin sets this value manually on the admin-frontend.
        this.sessionGroupName = "";     // Admin sets this value manually on the admin-frontend.
        this.volunteeringRoleName = ""; // Admin sets this value manually on the admin-frontend
    }
}
