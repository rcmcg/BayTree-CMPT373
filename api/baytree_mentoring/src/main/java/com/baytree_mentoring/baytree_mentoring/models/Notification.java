package com.baytree_mentoring.baytree_mentoring.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationId;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 10)
    private String messageBody;

    public Notification(String username, String messageBody) {
        this.username = username;
        this.messageBody = messageBody;
    }
}
