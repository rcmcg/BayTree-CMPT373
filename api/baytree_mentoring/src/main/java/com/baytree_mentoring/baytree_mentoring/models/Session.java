package com.baytree_mentoring.baytree_mentoring.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

    @NotNull
    private String clockInTimeLocal;

    @NotNull
    private String clockOutTimeLocal;

    private Instant clockInTimeUTC;

    private Instant clockOutTimeUTC;

    @NotNull
    private String sessionNotes;

    // TODO: Add the following fields to a session: mentorId, leadStaff, sessionGroupId,
    //  venueId(unless this is associated with the sessionGroup in Views, not any particular session)



    public Session(long menteeId, String clockInTimeLocal, String clockOutTimeLocal, String sessionNotes) {
        this.menteeId = menteeId;
        this.clockInTimeLocal = clockInTimeLocal;
        this.clockOutTimeLocal = clockOutTimeLocal;
        this.sessionNotes = sessionNotes;

        this.clockInTimeUTC = convertToUTC(clockInTimeLocal);
        this.clockOutTimeUTC = convertToUTC(clockOutTimeLocal);
    }

    private Instant convertToUTC(String time) {
        String pattern = "uuuu-MM-dd HH:mm:ss Z"; //Example: "2021-10-03 10:11:23 -0400"
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return localDateTime.atZone(ZoneId.of(time.substring(20))).toInstant();
    }
}

