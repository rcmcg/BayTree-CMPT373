import * as React from 'react';

const axios = require('axios').default;

class SelectMentee extends React.Component {
    render () {
        return (
            <div>
                <label form="selectMenteeId">Mentee id</label>
                <input type="number" id="selectMenteeId" name="menteeId" required/>
            </div>
        )
    }
}

class ClockIn extends React.Component {
    render() {
        return (
            <div>
                <label form="clockInId">Session clock in date and time</label>
                <input type="datetime-local" id="clockInId" name="clockInTimeLocal" required/>
            </div>
        );
    }
}

class ClockOut extends React.Component {
    render() {
        return (
            <div>
                <label form="clockOutId">Session clock out date and time</label>
                <input type="datetime-local" id="clockOutId" name="clockOutTimeLocal" required/>
            </div>
        );
    }
}

class SessionNotes extends React.Component {
    render () {
        return (
            <div>
                <label form="sessionNotes">Session notes</label>
                <input type="text" id="sessionNotesId" name="sessionNotes"/>
            </div>
        )
    }
}

class SessionSubmit extends React.Component {
    render() {
        return (
            <div>
                <input type="submit" value="Submit"/>
            </div>
        )
    }
}

export class SessionInformation extends React.Component {
    render () {
        return (
            <main>
                <form action={"http://localhost:8080/session/add"} method={"post"}>
                    <SelectMentee />
                    <ClockIn />
                    <ClockOut />
                    <SessionNotes />
                    <SessionSubmit />
                </form>
            </main>
        );
    }
}