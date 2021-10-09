import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../App";

const axios = require('axios').default;

interface MonthlyQuestionnaireState {
    mentorName: string,
    menteeName: string,
    submissionTimeLocal: string,
    month: number,
    menteeEngagementScore: number,
    menteeArrivalScore: number
}

class SelectMentor extends React.Component {
    render () {
        return (
            <div>
                <label form="mentorNameId">Mentor Name</label>
                <input type="text" id="mentorNameId" name="mentorName" required/>
            </div>
        )
    }
}

class SelectMentee extends React.Component {
    render () {
        return (
            <div>
                <label form="menteeNameId">Mentee Name</label>
                <input type="text" id="menteeNameId" name="menteeName" required/>
            </div>
        )
    }
}

class SubmissionTime extends React.Component {
    render() {
        return (
            <div>
                <label form="submissionTimeId">Session clock in date and time</label>
                <input type="datetime-local" id="submissionTimeId" name="submissionTimeLocal" required/>
            </div>
        );
    }
}

class SelectMonth extends React.Component {
    render () {
        return (
            <div>
                <label form="monthId">Month</label>
                <select id="monthId" name="month" required>
                    <option value="">N/A</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>
        )
    }
}

class SelectMenteeEngagementScore extends React.Component {
    render () {
        return (
            <div>
                <label form="menteeEngagementScoreId">Mentee Engagement Score</label>
                <select id="menteeEngagementScoreId" name="menteeEngagementScore" required>
                    <option value="">N/A</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
        )
    }
}

class SelectMenteeArrivalScore extends React.Component {
    render () {
        return (
            <div>
                <label form="menteeArrivalScoreId">Mentee Arrival Score</label>
                <select id="menteeArrivalScoreId" name="menteeArrivalScore" required>
                    <option value="">N/A</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
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

export class MonthlyQuestionnaireForm extends React.Component<{}, MonthlyQuestionnaireState> {
    constructor(props: any) {
        super(props);
        this.state = {
            mentorName: '',
            menteeName: '',
            submissionTimeLocal: '',
            month: -1,
            menteeEngagementScore: -1,
            menteeArrivalScore: -1
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.formatLocalDateTimeForBackend = this.formatLocalDateTimeForBackend.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleSubmit(event: any) {
        this.setState({
            mentorName: event.target.mentorNameId.value,
            menteeName: event.target.menteeNameId.value,
            submissionTimeLocal: event.target.submissionTimeId.value,
            month: event.target.monthId.value,
            menteeEngagementScore: event.target.menteeEngagementScoreId.value,
            menteeArrivalScore: event.target.menteeArrivalScoreId.value
        }, this.processUserSubmission)
        event.target.reset()
        event.preventDefault()
    }

    processUserSubmission() {
        // TODO: Verify clock in/out time is valid (in < out, total time less than some number of hours)
        const url = backendApiURL + '/monthlyquestionnaire/add/'
        axios.post(url, {
            mentorName: this.state.mentorName,
            menteeName: this.state.menteeName,
            submissionTimeLocal: this.formatLocalDateTimeForBackend(this.state.submissionTimeLocal),
            month: this.state.month,
            menteeEngagementScore: this.state.menteeEngagementScore,
            menteeArrivalScore: this.state.menteeArrivalScore
        })
            .then(function (response: AxiosResponse) {
                console.log(response);
                if (response.status === HTTP_CREATED_STATUS_RESPONSE) {
                    // TODO: Remove and replace with user friendly success response
                    alert('Server responded with status 201 (object CREATED after POST request)')
                }
            })
            .catch(function (error: AxiosError) {
                // TODO: Interpret and display a relevant message for user
                // E.g., "You've already uploaded this monthly questionnaire"
                console.log(error);
            })
    }

    formatLocalDateTimeForBackend (timeLocal: string) {
        // Datetime sent to backend must be in format YYYY-MM-DD HH:MM:SS Timezone (from universal tz database)
        // Ex: 2021-09-28 20:12:12 America/Vancouver
        return timeLocal.slice(0, 10) + ' ' + timeLocal.slice(11, 16) + ':00'
            + ' ' + Intl.DateTimeFormat().resolvedOptions().timeZone;
    }

    render() {
        return (
            <main>
                <form onSubmit={this.handleSubmit}>
                    <SelectMentor />
                    <SelectMentee />
                    <SubmissionTime />
                    <SelectMonth />
                    <SelectMenteeEngagementScore />
                    <SelectMenteeArrivalScore />
                    <SessionSubmit />
                </form>
            </main>
        );
    }
}
