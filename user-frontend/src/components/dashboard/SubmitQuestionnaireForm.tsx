import * as React from 'react';
import {backendApiURL} from "../../App";
import axios, {AxiosResponse} from "axios";
import {useState} from "react";
import LoadingDots from "../LoadingDots";

export class SubmitQuestionnaireForm extends React.Component<any, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            loading: true,
            menteeId: -1,
            mentorId: -1,
            year: -1,
            month: -1,
            questionsResponse: "",
            questions: "",
            categories: [],
            inputTypes: [],
            validation: [],
            answers: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
    }


    componentDidMount() {
        this.getUrlParametersUpdateState();
        // Get list of questions from the backend
        console.log(this.state)
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
            .then((response:AxiosResponse) => {
                console.log(response);
                this.buildQuestionnaireForm(response.data)
            })
            .catch(function (error) {
                alert('Failed to grab questionnaire. Please try again later or contact your administrator.')
                console.log(error)
            })
    }

    private buildQuestionnaireForm(questionnaireJSON: any) {
        // console.log("Inside buildQuestionnaireForm")
        // console.log(questionnaireJSON)
        // console.log("Question: " + questionnaireJSON.Question)
        let questionsArray = []
        let categoryArray = []
        let inputTypeArray = []
        let validationArray = []
        for (let key in questionnaireJSON) {
            // console.log(key + " -> " + questionnaireJSON[key])
            // console.log(questionnaireJSON[key]['Question'])
            questionsArray.push(questionnaireJSON[key]['Question'])
            categoryArray.push(questionnaireJSON[key]['category'])
            inputTypeArray.push(questionnaireJSON[key]['inputType'])
            validationArray.push(questionnaireJSON[key]['validation'])
        }
        // console.log("questionsArray: " + questionsArray)
        // console.log("questionsArray[0]: " + questionsArray[0])
        this.setState({
            loading: false,
            questionsResponse: questionnaireJSON,
            questions: questionsArray,
            categories: categoryArray,
            inputTypes: inputTypeArray,
            validation: validationArray
        })
    }

    private static parseYearMonthFromDateInput(selectYearMonth: string | null) {
        if (selectYearMonth === null) {
            return "";
        }
        return selectYearMonth.split('-');
    }

    handleSubmit(event: any) {
        event.preventDefault()
        let element = null;
        for (let i = 0; i < this.state.questions.length; i++) {
            element = document.getElementById("question" + i)
            console.log(element)
            if (element != null) {
                console.log((element as HTMLInputElement).value)
            }
        }
    }

    render() {
        if (this.state.loading) {
            return (
                <main>
                    <span>Loading</span>
                    <LoadingDots />
                </main>
            )
        } else if (this.state.questions != null) {
            return (
                <main>
                    <div className={"ui form sessionForm"}>
                        <form onSubmit={this.handleSubmit} id={"questionnaire-form"}>
                            {Object.keys(this.state.questions).map((key) => {
                                return (
                                    <div>
                                        <div>
                                            <label htmlFor={"question" + key}>
                                                {this.state.questions[parseInt(key)]} (Enter value 1 to 5)
                                            </label>
                                            <input
                                                type={this.state.inputTypes[parseInt(key)]}
                                                id={"question" + key} name={"question" + key}/>
                                        </div>
                                        <br/>
                                    </div>
                                )
                            })}
                            <span className={"submitButtonFormat"}>
                                <button className={"ui primary button"} type={"submit"} onSubmit={this.handleSubmit}>
                                    Submit
                                </button>
                                <br/>
                            </span>
                        </form>
                    </div>
                </main>
            )
        }
    }
}