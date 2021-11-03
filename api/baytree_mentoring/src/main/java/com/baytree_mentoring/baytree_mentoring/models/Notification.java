package com.baytree_mentoring.baytree_mentoring.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor

public class Notification {
    @Id
    private long notificationId;

    @NotNull
    private String username;

    @NotNull
    private String messageBody;

    public Notification(long notificationId, String username, String messageBody) {
        this.notificationId = notificationId;
        this.username = username;
        this.messageBody = messageBody;
    }
}
