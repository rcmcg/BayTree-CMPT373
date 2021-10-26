package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    public User(int viewsId, String firstName, String lastName, String email, String status) {
        this.viewsId = viewsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public User(int viewsId, String firstName, String lastName, String name, String email,
                String status, String startDate, String endDate, String phoneNumber,
                String ethnicity, String address, String role) {
        this.viewsId = viewsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.email = email;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
        this.ethnicity = ethnicity;
        this.address = address;
        this.role = role;
    }

    @Id
    private int viewsId;

    String firstName;

    String lastName;

    String name;

    String email;

    String status;

    String startDate;

    String endDate;

    String phoneNumber;

    String ethnicity;

    String address;

    String role;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getAddress() {
        return address;
    }

    public int getViewsId() {
        return viewsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
