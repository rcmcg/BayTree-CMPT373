import * as React from 'react';
import {backendApiURL} from "../App";
import axios from "axios";

export class SubmitQuestionnaireForm extends React.Component<any, any> {
    state = {
        loading: true,
        menteeId: -1,
        mentorId: -1,
        year: -1,
        month: -1,
        questions: [],
        answers: []
    }

    componentDidMount() {
        this.getUrlParametersUpdateState();
        // Get list of questions from the backend
        console.log(this.state)
        // this.getListOfQuestionsFromBackendUpdateState();
    }

    getUrlParametersUpdateState() {
        const windowUrl = window.location.search;
        const params = new URLSearchParams(windowUrl);
        let dateArray = SubmitQuestionnaireForm.parseYearMonthFromDateInput(params.get('selectYearMonth'));
        let yearUrlInt: number = parseInt(dateArray[0]);
        let monthUrlInt: number = parseInt(dateArray[1]);
        this.setState({
            menteeId: parseInt(params.get('menteeId') as string),
            mentorId: parseInt(params.get('mentorId') as string),
            year: yearUrlInt,
            month: monthUrlInt
        }, this.getListOfQuestionsFromBackendUpdateState)
    }

    async getListOfQuestionsFromBackendUpdateState() {
        let url = backendApiURL + '/monthlyquestionnaire/?year=' + this.state.year + '&month=' + this.state.month;
        // let url = backendApiURL + '/monthlyquestionnaire/?year=' + this.state.year;
        console.log("Sending axios get request with URL: " + url);
        axios.get(url)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    private static parseYearMonthFromDateInput(selectYearMonth: string | null) {
        if (selectYearMonth === null) {
            return "";
        }
        return selectYearMonth.split('-');
    }

    render() {
        if (this.state.loading) {
            return (
                <div>Loading...</div>
            )
        }
        return (
            <div>Done loading</div>
        )
    }
}