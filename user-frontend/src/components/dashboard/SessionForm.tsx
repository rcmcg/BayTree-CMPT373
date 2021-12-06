import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";
import 'moment-timezone';
import "../../css/dashboard/SessionForm.css"
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App";

const axios = require('axios').default;
const moment = require('moment');
moment().format();

interface SessionState {
    menteeId: string,
    mentorId: number,
    sessionGroupId: number,
    didMenteeAttend: boolean,
    didMentorAttend: boolean,
    clockInTimeLocal: string,
    clockOutTimeLocal: string,
    sessionNotes: string,
    menteesList: []
}

interface props {
    menteesList: []
}

class SelectMentee extends React.Component<props> {
    render () {
        return (
            <div>
                <label form="selectMenteeId"> Mentee Name </label>
                <select id={"selectMenteeId"} name={"menteeId"}>
                    <option value={""}>Select a mentee</option>
                    {this.props.menteesList.map(mentee => <option value = {mentee["participantId"]}>{mentee["firstName"] + " " + mentee["lastName"]}</option>)}
                </select>
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
                <textarea id="sessionNotesId" name="sessionNotes"/>
            </div>
        )
    }
}

class SessionSubmit extends React.Component {
    render() {
        return (
            <div>
                <button className={"ui primary button"}>
                    Submit
                </button>
            </div>
        )
    }
}

export class SessionForm extends React.Component<{}, SessionState> {
    constructor(props: any) {
        super(props);
        this.state = {
            menteeId: '',
            mentorId: 42,   // Set this value to the mentor's ID when authentication is fully working
            sessionGroupId: -1,
            didMenteeAttend: true,
            didMentorAttend: true,
            clockInTimeLocal: '',
            clockOutTimeLocal: '',
            sessionNotes: '',
            menteesList: []
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.getMenteesList = this.getMenteesList.bind(this);
        this.getSessionGroupId = this.getSessionGroupId.bind(this);
        this.formatLocalDateTimeForBackend = this.formatLocalDateTimeForBackend.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    componentDidMount() {
        this.getMenteesList()
        this.getSessionGroupId()
    }

    getMenteesList() {
        axios.get('http://localhost:8080/fetchAllMentees')
            .then((res: AxiosResponse) => {
                if(res.data !== null) {
                    this.setState({ menteesList : res.data });
                }
            })
            .catch((err: AxiosError) => {
                console.log(err);
            })
    }

    getSessionGroupId() {
        let url = backendApiURL + '/user/mentors/' + this.state.mentorId + '/sessiongroup'
        console.log("Getting sessionGroupId with URL " + url)
        axios.get(url)
            .then((res: AxiosResponse) => {
                console.log(res)
                if (res.data !== null) {
                    this.setState( {sessionGroupId : res.data })
                }
            })
            .catch((err: AxiosError) => {
            console.log(err);
            })
    }

    handleSubmit(event: any) {
        this.setState({
            menteeId: event.target.selectMenteeId.value,
            didMenteeAttend: event.target.didMenteeAttend.checked,
            didMentorAttend: event.target.didMentorAttend.checked,
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
        alert('Submitting session upload. This may take a few minutes. Press okay to continue.')
        console.log("State being submitted to backend")
        console.log(this.state)
        axios.post(url, {
            menteeId: this.state.menteeId,
            mentorId: this.state.mentorId,
            sessionGroupId: this.state.sessionGroupId,
            didMenteeAttend: this.state.didMenteeAttend,
            didMentorAttend: this.state.didMentorAttend,
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
            alert('Failed to upload session. Please try again later.')
            console.log(error);
        })
    }

    formatLocalDateTimeForBackend (timeLocal: string) {
        // Datetime sent to backend must be in format YYYY-MM-DD HH:MM:SS Timezone-Offset(hours)
        // Ex: 2021-09-28 20:12:12 -0800
        let offset = moment(timeLocal).utcOffset();
        let offsetHours = Math.trunc(offset / 60);
        let offsetMinutes = Math.abs(offset % 60);

        let offsetHoursString = offsetHours.toString()
        if (Math.abs(offsetHours) < 10) {
            // insert a 0 between the '-' (index 0) and the first digit (index 1)
            offsetHoursString = offsetHoursString.slice(0,1) + '0' + offsetHoursString.slice(1)
        }
        let offsetUTCHoursAndMinutesString = offsetHoursString + offsetMinutes.toString();

        return timeLocal.slice(0, 10) + ' ' + timeLocal.slice(11, 16) + ':00'
            + ' ' + offsetUTCHoursAndMinutesString;
    }

    render() {
        return (
            <main>
                <div className={"ui form sessionForm"}>
                    <form onSubmit={this.handleSubmit}>
                        <SelectMentee menteesList={this.state.menteesList} /> <br/>
                        <DidMenteeAttendSession /> <br/>
                        <DidMentorAttendSession /> <br/>
                        <ClockIn /> <br/>
                        <ClockOut /> <br/>
                        <SessionNotes /> <br/>
                        <span className={"submitButtonFormat"}>
                        <SessionSubmit /> <br />
                    </span>
                    </form>
                </div>
            </main>
        );
    }
}