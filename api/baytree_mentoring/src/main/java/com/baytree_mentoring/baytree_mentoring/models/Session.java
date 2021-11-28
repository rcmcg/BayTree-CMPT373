package com.baytree_mentoring.baytree_mentoring.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mentoringSessionId;

    @NotNull
    private long menteeId;

    private long mentorId;

    private long sessionGroupId;

    private boolean didMenteeAttend;

    private boolean didMentorAttend;

    @NotNull
    private String clockInTimeLocal;

    @NotNull
    private String clockOutTimeLocal;

    private long leadStaffId;

    @NotNull
    private String sessionNotes;

    public Session(long menteeId, long mentorId, long sessionGroupId, boolean didMenteeAttend, boolean didMentorAttend,
                   String clockInTimeLocal, String clockOutTimeLocal,
                   long leadStaffId, String sessionNotes) {
        this.menteeId = menteeId;
        this.mentorId = mentorId;
        this.sessionGroupId = sessionGroupId;
        this.didMenteeAttend = didMenteeAttend;
        this.didMentorAttend = didMentorAttend;
        this.clockInTimeLocal = clockInTimeLocal;
        this.clockOutTimeLocal = clockOutTimeLocal;
        this.leadStaffId = leadStaffId;
        this.sessionNotes = sessionNotes;
    }
}

