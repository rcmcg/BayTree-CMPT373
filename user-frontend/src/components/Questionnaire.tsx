import React, { useState} from 'react';
import "../css/Questionnaire.css"
import {ListBody, ListMonth} from "./QuestionnairesList"
import {Link} from "react-router-dom";

// dummy objects
export interface IState {
    questionnaires: {
        id: number
        title: string
        description: string
        type: string
    }[]
}

function Questionnaire() {

    // dummy data
    const [questionnaires] = useState<IState["questionnaires"]>([
        {   id: 7, title: "Sample 1",
            description: "Dummy sample to test UI", type:"General Agency"},
        {   id: 8, title: "Sample 2",
            description: "Dummy sample to test UI", type:"General Contract"},
        {   id: 9, title: "Sample 3",
            description: "Dummy sample to test UI", type:"General Session"},
        {   id: 10, title: "Sample 4",
            description: "Dummy sample to test UI", type:"End of session"},
        {   id: 11, title: "Sample 5",
            description: "Dummy sample to test UI", type:"End of 121 Section"},
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
                    <ListMonth questionnaires={questionnaires} />
                </div>
            </section>

        </div>
    );
}

export default Questionnaire;