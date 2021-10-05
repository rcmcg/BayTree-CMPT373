import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";

const axios = require('axios').default;
const backendApiURL = "http://localhost:8080"

interface SessionState {
    menteeId: number,
    clockInTimeLocal: string,
    clockOutTimeLocal: string,
    sessionNotes?: string
}

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

export class SessionForm extends React.Component<{}, SessionState> {
    constructor(props: any) {
        super(props);
        this.state = {
            menteeId: -1,
            clockInTimeLocal: '',
            clockOutTimeLocal: '',
            sessionNotes: ''
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event: any) {
        this.setState({
            menteeId: event.target.selectMenteeId.value,
            clockInTimeLocal: event.target.clockInId.value,
            clockOutTimeLocal: event.target.clockOutId.value,
            sessionNotes: event.target.sessionNotesId.value
        }, this.processUserSubmission)
        event.preventDefault()
    }

    formatLocalDateTimeForBackend (timeLocal: string) {
        // Datetime sent to backend must be in format YYYY-MM-DD HH:MM:SS Timezone
        // Ex: 2021-09-28 20:12:12 America/Vancouver
        return timeLocal.slice(0, 10) + ' ' + timeLocal.slice(11, 16) + ':00'
            + ' ' + Intl.DateTimeFormat().resolvedOptions().timeZone;
    }

    processUserSubmission() {
        const url = backendApiURL + '/session/add/'
        axios.post(url, {
            menteeId: this.state.menteeId,
            clockInTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockInTimeLocal),
            clockOutTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockOutTimeLocal),
            sessionNotes: this.state.sessionNotes
        })
        .then(function (response: AxiosResponse) {
            console.log(response);
        })
        .catch(function (error: AxiosError) {
            console.log(error);
        })
    }

    render() {
        return (
            <main>
                <form onSubmit={this.handleSubmit}>
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