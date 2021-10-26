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

    public User(int viewsId, String firstName, String lastName, String email, String status, String startDate, String endDate, String phoneNumber, String ethnicity, String address) {
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
    }

    @Id
    private int viewsId;

    String firstName;

    String lastName;

    String email;

    String status;

    String startDate;

    String endDate;

    String phoneNumber;

    String ethnicity;

    String address;

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
