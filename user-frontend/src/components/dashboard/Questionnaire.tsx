import React, { useState} from 'react';
import "../../css/dashboard/Questionnaire.css"
import {ListBody, ListTitle} from "./QuestionnairesList"
import {Link} from "react-router-dom";

// dummy objects
export interface IState {
    questionnaires: {
        id: number
        title: string
        mentorName: string
        menteeName: string
        date: string
        month: string
        engagementScore: number
        arrivalScore:number
    }[]
}

function Questionnaire() {

    // dummy data
    const [questionnaires] = useState<IState["questionnaires"]>([
        {   id: 7, title: "Sample 1",
            mentorName: "Jason", menteeName:"Jisoo",
            date: "20-9-2012", month: "September",
            engagementScore: 4, arrivalScore: 3},
        {   id: 8, title: "Sample 2",
            mentorName: "Jason", menteeName:"Jisoo",
            date: "17-9-2012", month: "September",
            engagementScore: 4, arrivalScore: 3},
        {   id: 9, title: "Sample 3",
            mentorName: "Jason", menteeName:"Jisoo",
            date: "20-9-2012", month: "September",
            engagementScore: 4, arrivalScore: 3},
        {   id: 10, title: "Sample 4",
            mentorName: "Jason", menteeName:"Jisoo",
            date: "20-9-2012", month: "September",
            engagementScore: 4, arrivalScore: 3},
        {   id: 11, title: "Sample 5",
            mentorName: "Jason", menteeName:"Jisoo",
            date: "20-9-2012", month: "September",
            engagementScore: 4, arrivalScore: 3},
    ])

    return (

        <div className={"questionnaire"}>

            <h5 className={"header-component"}>
                Monthly Questionnaires
                <Link to={'/monthlyquestionnaire'}>
                    <div className={"post-bttn"}>
                        Add
                    </div>
                </Link>
            </h5>

            <section className={"body-questionnaire"}>
                <div className={"questionnaire-box"}>
                    <ListBody questionnaires={questionnaires} />
                </div>
                <div className={"month"}>
                    <ListTitle questionnaires={questionnaires} />
                </div>
            </section>

        </div>
    );
}

export default Questionnaire;