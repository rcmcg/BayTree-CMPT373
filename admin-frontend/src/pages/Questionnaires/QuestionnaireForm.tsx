import * as React from 'react';
import axios, {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App";

interface QuestionnaireState {
    year: number,
    month: number,
    viewsQuestionnaireId: number,
    existingQuestionnaires: string[]
}

class SelectYearMonth extends React.Component {
    render() {
        return (
            <div>
                <label form="selectMonth">Month </label>
                <input type="month" id="selectYearMonth" name="month" required/>
            </div>
        )
    }
}

class SelectViewsQuestionnaireId extends React.Component {
    render () {
        return (
            <div>
                <label form="selectQuestionnaireId">Questionnaire id </label>
                <input type="number" id="selectQuestionnaireId" name="questionnaireId" required/>
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

export class QuestionnaireForm extends React.Component<{}, QuestionnaireState>{
    constructor(props: any) {
        super(props);
        this.state = {
            year: -1,
            month: -1,
            viewsQuestionnaireId: -1,
            existingQuestionnaires:
                ["This will contain a list of existing (year-month, viewsQuestionnaireId) pairs in our database",
                "2nd entry for testing"]
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.parseYearMonthFromDateInput = this.parseYearMonthFromDateInput.bind(this);
        this.processUserSubmission = this.processUserSubmission.bind(this);
    }

    handleSubmit(event: any) {
        let dateArray = this.parseYearMonthFromDateInput(event.target.selectYearMonth.value);
        let yearInput: string = dateArray[0];
        let monthInput: string = dateArray[1];
        let yearInputInt: number = parseInt(yearInput);
        let monthInputInt: number = parseInt(monthInput);
        this.setState({
            year: yearInputInt,
            month: monthInputInt,
            viewsQuestionnaireId: parseInt(event.target.selectQuestionnaireId.value)
        }, this.processUserSubmission)
        event.preventDefault();
    }

    parseYearMonthFromDateInput(date: string) {
        return date.split('-');
    }

    processUserSubmission() {
        console.log("processUserSubmission():");
        console.log(this.state);
        const url = backendApiURL + '/questionnaire/add';
        axios.post(url, {
            year: this.state.year,
            month: this.state.month,
            viewsQuestionnaireId: this.state.viewsQuestionnaireId
        })
        .then(function (response: AxiosResponse<any>) {
            console.log(response);
            if(response.status === HTTP_CREATED_STATUS_RESPONSE) {
                alert('Success! Your choice of questionnaire has been uploaded');
            }
        })
        .catch(function (error: AxiosError) {
            alert('Failed to upload questionnaire choice. Please try again later');
            console.log(error);
        })
    }

    render() {
        return (
            <main>
                <form onSubmit={this.handleSubmit}>
                    <SelectYearMonth />
                    <SelectViewsQuestionnaireId />
                    <QuestionnaireSubmit />
                </form>
                <div>QuestionnaireForm</div>
            </main>
        )
    }
}