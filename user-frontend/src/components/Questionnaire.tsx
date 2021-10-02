import React, { useState} from 'react';
import "../css/Questionnaire.css"
import {ListBody, ListMonth} from "./QuestionnairesList"

// dummy objects
export interface IState {
    questionnaires: {
        id: number
        month: string
    }[]
}

function Questionnaire() {

    const [questionnaires] = useState<IState["questionnaires"]>([
        {   id: 7, month: "July"},
        {   id: 8, month: "August"},
        {   id: 9, month: "September"},
        {   id: 10, month: "October"},
        {   id: 11, month: "November"},
    ])

    return (

        <div className={"questionnaire"}>

            <h5 className={"header-component"}>
                Monthly Questionnaires
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