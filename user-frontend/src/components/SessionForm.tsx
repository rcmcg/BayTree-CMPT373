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
    mentorId: number,
    sessionGroupId: number,
    didMenteeAttend: boolean,   // todo
    didMentorAttend: boolean,   // todo
    clockInTimeLocal: string,
    clockOutTimeLocal: string,
    leadStaffId: number,        // todo
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

// class DidMentorAttendSession extends React.Component {
//     // handleInputChange(event: React.ChangeEvent<HTMLInputElement>) {
//     //     this.setState({didMentorAttend: event.target.checked});
//     // }
//
//     render () {
//         return (
//             <div>
//                 <label form="didMentorAttend">Did the mentor attend the session?</label>
//                 <input
//                     type="checkbox"
//                     id="didMentorAttend"
//                     name="didMentorAttend"
//                     onChange= {this.props.handleMentorAttendanceCheckbox}
//                 />
//             </div>
//         )
//     }
// }

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
                <label form="sessionNotes">Session notes </label>
                <input type="text" id="sessionNotesId" name="sessionNotes" required/>
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
            mentorId: -1,           // todo
            sessionGroupId: -1,     // todo
            didMenteeAttend: true,   // todo
            didMentorAttend: true,   // todo
            clockInTimeLocal: '',
            clockOutTimeLocal: '',
            leadStaffId: -1,        // todo
            sessionNotes: ''
        }
        this.handleMentorAttendanceCheckbox = this.handleMentorAttendanceCheckbox.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.formatLocalDateTimeForBackend = this.formatLocalDateTimeForBackend.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleMentorAttendanceCheckbox() {
        this.setState({
            didMentorAttend: !(this.state.didMentorAttend)
        })
    }

    handleSubmit(event: any) {
        this.setState({
            menteeId: event.target.selectMenteeId.value,
            mentorId: event.target.selectMentorId.value,
            sessionGroupId: event.target.selectSessionGroupId.value,
            // didMentorAttend: event.target.didMentorAttend.value,
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
        console.log(this.state)
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
                    <SelectMentor />
                    <SelectSessionGroupId />
                    {/*<DidMentorAttendSession handleMentorAttendanceCheckbox = {this.handleMentorAttendanceCheckbox}/>*/}
                    <ClockIn />
                    <ClockOut />
                    <SelectLeadStaffId />
                    <SessionNotes />
                    <SessionSubmit />
                </form>
            </main>
        );
    }
}