import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";
import Moment from 'react-moment';
import 'moment-timezone';
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../App";

const axios = require('axios').default;
const moment = require('moment');
moment().format();

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
                <label form="selectMenteeId">Mentee id </label>
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
                <label form="sessionNotes">Session notes </label>
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
        this.formatLocalDateTimeForBackend = this.formatLocalDateTimeForBackend.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleSubmit(event: any) {
        this.setState({
            menteeId: event.target.selectMenteeId.value,
            clockInTimeLocal: event.target.clockInId.value,
            clockOutTimeLocal: event.target.clockOutId.value,
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
            clockInTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockInTimeLocal),
            clockOutTimeLocal: this.formatLocalDateTimeForBackend(this.state.clockOutTimeLocal),
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