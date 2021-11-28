package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
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
        this.sessionGroupId = -1;
    }
}
