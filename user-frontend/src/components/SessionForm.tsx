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

    isClockInClockOutTimeValid (clockInTimeLocal: string, clockOutTimeLocal: string) {
        // TODO: Check clockInTimeLocal <  clockOutTimeLocal.
        return null;
    }

    processUserSubmission() {
        // This function is called only after the state has been updated
        // TODO: Create and send POST request to backend, interpret and display response
        console.log('State has finished updating')
        let message:string = this.state.menteeId + ' ' + this.state.clockInTimeLocal + this.state.clockOutTimeLocal
            + ' ' + this.state.sessionNotes
        alert('Information was submitted: ' + message)
        const url = backendApiURL + '/session/add/'
        console.log('Sending JSON to ' + url)
        axios.post(url, {
            menteeId: this.state.menteeId,
            // clockInTimeLocal: this.state.clockInTimeLocal,
            clockInTimeLocal: "2021-09-28 20:12:12 America/Vancouver",
            // clockOutTimeLocal: this.state.clockOutTimeLocal,
            clockOutTimeLocal: "2021-09-28 21:12:12 America/Vancouver",
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
                {/*<form action={"http://localhost:8080/session/add"} method={"post"}>*/}
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