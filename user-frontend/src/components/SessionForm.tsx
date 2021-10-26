import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";
import 'moment-timezone';
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../App";

const axios = require('axios').default;
const moment = require('moment');
moment().format();

interface SessionState {
    menteeId: number,
    mentorId: number,
    sessionGroupId: number,
    didMenteeAttend: boolean,
    didMentorAttend: boolean,
    clockInTimeLocal: string,
    clockOutTimeLocal: string,
    leadStaffId: number,
    sessionNotes: string
}

class SelectMentee extends React.Component {
    render () {
        return (
            <div>
                <label form="selectMenteeId">Mentee id </label>
                <input type="number" id="selectMenteeId" name="menteeId" required/>
            </div>
        )
    }
}

class SelectMentor extends React.Component {
    render () {
        return (
            <div>
                <label form="selectMentorId">Mentor id </label>
                <input type="number" id="selectMentorId" name="mentorId" required/>
            </div>
        )
    }
}

class SelectSessionGroupId extends React.Component {
    render () {
        return (
            <div>
                <label form="selectSessionGroupId">Session group id </label>
                <input type="number" id="selectSessionGroupId" name="sessionGroupId" required/>
            </div>
        )
    }
}

class DidMenteeAttendSession extends React.Component {
    render () {
        return (
            <div>
                <label form="didMenteeAttend">Did the mentee attend the session?</label>
                <input type="checkbox" id="didMenteeAttend" name="didMenteeAttend" defaultChecked/>
            </div>
        )
    }
}

class DidMentorAttendSession extends React.Component {
    render () {
        return (
            <div>
                <label form="didMentorAttend">Did the mentor attend the session?</label>
                <input type="checkbox" id="didMentorAttend" name="didMentorAttend" defaultChecked/>
            </div>
        )
    }
}

class SelectLeadStaffId extends React.Component {
    render () {
        return (
            <div>
                <label form="selectLeadStaffId">Lead staff (supervisor) id </label>
                <input type="number" id="selectLeadStaffId" name="leadStaffId" required/>
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
                <label form="sessionNotes">
                    Session notes. If you or the mentee did not attend the session, please explain why.
                </label>
                <textarea id={"sessionNotesId"} name={"sessionNotes"} rows={5} cols={33}/>
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
            mentorId: -1,
            sessionGroupId: -1,
            didMenteeAttend: true,
            didMentorAttend: true,
            clockInTimeLocal: '',
            clockOutTimeLocal: '',
            leadStaffId: -1,
            sessionNotes: ''
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.formatLocalDateTimeForBackend = this.formatLocalDateTimeForBackend.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleSubmit(event: any) {
        this.setState({
            menteeId: event.target.selectMenteeId.value,
            mentorId: event.target.selectMentorId.value,
            sessionGroupId: event.target.selectSessionGroupId.value,
            didMenteeAttend: event.target.didMenteeAttend.checked,
            didMentorAttend: event.target.didMentorAttend.checked,
            clockInTimeLocal: event.target.clockInId.value,
            clockOutTimeLocal: event.target.clockOutId.value,
            leadStaffId: event.target.selectLeadStaffId.value,
            sessionNotes: event.target.sessionNotesId.value
        }, this.processUserSubmission)
        event.target.reset()
        event.preventDefault()
    }

    processUserSubmission() {
        // TODO: Verify clock in/out time is valid (in < out, total time less than some number of hours)
        const url = backendApiURL + '/session/add/'
        axios.post(url, {
            menteeId: this.state.menteeId,
            mentorId: this.state.mentorId,
            sessionGroupId: this.state.sessionGroupId,
            didMenteeAttend: this.state.didMenteeAttend,
            didMentorAttend: this.state.didMentorAttend,
            clockInTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockInTimeLocal),
            clockOutTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockOutTimeLocal),
            leadStaffId: this.state.leadStaffId,
            sessionNotes: this.state.sessionNotes
        })
        .then(function (response: AxiosResponse) {
            console.log(response);
            if (response.status === HTTP_CREATED_STATUS_RESPONSE) {
                // TODO: Remove and replace with user friendly success response
                alert('Success! Session information uploaded.')
            }
        })
        .catch(function (error: AxiosError) {
            // TODO: Interpret and display a relevant message for user
            // E.g., "You've already uploaded a session for this date"
            alert('Failed to upload session. Please try again later.')
            console.log(error);
        })
    }

    formatLocalDateTimeForBackend (timeLocal: string) {
        // Datetime sent to backend must be in format YYYY-MM-DD HH:MM:SS Timezone-Offset(hours)
        // Ex: 2021-09-28 20:12:12 -0800
        let offset = moment(timeLocal).utcOffset();
        let offsetHours = Math.trunc(offset/60);
        let offsetMinutes = Math.abs(offset % 60);

        let offsetHoursString = offsetHours.toString()
        if (Math.abs(offsetHours) < 10) {
            // insert a 0 between the '-' (index 0) and the first digit (index 1)
            offsetHoursString = offsetHoursString.slice(0,1) + '0' + offsetHoursString.slice(1)
        }
        let offsetUTCHoursAndMinutesString = offsetHoursString + offsetMinutes.toString();

        let formattedTime = timeLocal.slice(0, 10) + ' ' + timeLocal.slice(11, 16) + ':00'
            + ' ' + offsetUTCHoursAndMinutesString;
        return formattedTime;
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <SelectMentee/>
                    <SelectMentor/>
                    <SelectSessionGroupId/>
                    <DidMenteeAttendSession />
                    <DidMentorAttendSession />
                    <ClockIn/>
                    <ClockOut/>
                    <SelectLeadStaffId/>
                    <SessionNotes/>
                    <SessionSubmit/>
                </form>
            </div>
        )
    }
}