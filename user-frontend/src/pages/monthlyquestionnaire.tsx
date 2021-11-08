import * as React from 'react';
import "../css/dashboard/MonthlyQuestionnaireForm.css"

import {MonthlyQuestionnaireForm} from "../components/dashboard/MonthlyQuestionnaireForm";

export const MonthlyQuestionnaire = () => {
    return (
        <main>
            <h2 className={"monthlyQuestionnaireHeading"}>Monthly Questionnaire</h2>
            <MonthlyQuestionnaireForm />
        </main>
    );
};