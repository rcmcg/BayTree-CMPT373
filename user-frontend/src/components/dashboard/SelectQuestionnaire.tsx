import * as React from 'react';
import {AxiosError, AxiosResponse} from "axios";
import "../../css/dashboard/MonthlyQuestionnaireForm.css"
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App";

const axios = require('axios').default;

interface MonthlyQuestionnaireState {
    mentorId: number,
    menteeId: number,
    year: number,
    month: number
}

class SelectMentor extends React.Component {
    render () {
        return (
            <div>
                <label htmlFor="mentorId">Mentor Id (remove when app knows which mentor is logged in) </label>
                <input type="number" id="mentorId" name="mentorId" required/>
            </div>
        )
    }
}

class SelectMentee extends React.Component {
    render () {
        return (
            <div>
                <label htmlFor="menteeId">Mentee Id (replace with dropdown of associated mentees) </label>
                <input type="number" id="menteeId" name="menteeId" required/>
            </div>
        )
    }
}

class SelectYearMonth extends React.Component {
    render() {
        return (
            <div>
                <label htmlFor="selectYearMonth">Month </label>
                <input type="month" id="selectYearMonth" name="selectYearMonth" required/>
            </div>
        )
    }
}

class QuestionnaireSubmit extends React.Component {
    render() {
        return (
            <div>
                <button>Submit</button>
            </div>
        )
    }
}

export class SelectQuestionnaire extends React.Component<{}, MonthlyQuestionnaireState> {
    constructor(props: any) {
        super(props);
        this.state = {
            mentorId: -1,
            menteeId: -1,
            year: -1,
            month: -1,
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleSubmit(event: any) {
        console.log(event.target.selectYearMonth.value)
        let dateArray = this.parseYearMonthFromDateInput(event.target.selectYearMonth.value);
        let yearInput: string = dateArray[0];
        let monthInput: string = dateArray[1];
        let yearInputInt: number = parseInt(yearInput);
        let monthInputInt: number = parseInt(monthInput);
        this.setState({
            mentorId: event.target.mentorId.value,
            menteeId: event.target.menteeId.value,
            year: yearInputInt,
            month: monthInputInt
        }, this.processUserSubmission)
        // event.target.reset()
        // event.preventDefault()
    }

    parseYearMonthFromDateInput(date: string) {
        return date.split('-');
    }

    processUserSubmission() {
        // TODO: Verify clock in/out time is valid (in < out, total time less than some number of hours)
        console.log(this.state)
    }

    render() {
        return (
            <main>
                <div className={"ui form monthlyQuestionnaire"}>
                    <form onSubmit={this.handleSubmit} action={"/submitquestionnaire"} method={"GET"}>
                        <SelectMentor /> <br/>
                        <SelectMentee /> <br/>
                        <SelectYearMonth /> <br/>
                        <span className={"submitButtonFormat"}>
                            <QuestionnaireSubmit /> <br/>
                        </span>
                    </form>
                </div>
            </main>
        );
    }
}
