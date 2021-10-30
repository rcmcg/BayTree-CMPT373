import React from "react";

interface MentorSessionInterface {
    session: {
        title: string;
        startDateTime: string;
        duration: string;
        status: string;
        notes: string;
    };
}

function MentorSession(sessionData: MentorSessionInterface["session"]) {
    const date = new Date(sessionData.startDateTime)
    return (
        <p>
            <h3>{date.toDateString()}</h3>
            <h4>{sessionData.title}</h4>
            <strong>Start Time: </strong> {date.toLocaleTimeString("en-US")} <br/>
            <strong>Duration: </strong> {sessionData.duration} <br/>
            <strong>Attendance: </strong> {sessionData.status} <br/>
            <strong>Notes: </strong> {sessionData.notes} <br/>
        </p>
    );
}

export default MentorSession;