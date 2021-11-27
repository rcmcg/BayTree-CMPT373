import React from "react";
import { MentorSessionInterface } from "./MentorInterfaces"

function MentorSession(sessionData: MentorSessionInterface) {
    const date = new Date(sessionData.dateTime)
    return (
        <div>
            <h3>{date.toDateString()}</h3>
            <h4>{sessionData.sessionGroup}</h4>
            <strong>Start Time: </strong> {date.toLocaleTimeString("en-US")} <br/>
            <strong>Duration: </strong> {sessionData.duration} <br/>
            <strong>Attendance: </strong> {sessionData.attendance} <br/>
            <strong>Notes: </strong> {sessionData.note} <br/>
        </div>
    );
}

export default MentorSession;