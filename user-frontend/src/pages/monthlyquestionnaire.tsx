import * as React from 'react';
import "../css/dashboard/MonthlyQuestionnaireForm.css"

import {SelectQuestionnaire} from "../components/dashboard/SelectQuestionnaire"

export const MonthlyQuestionnaire = () => {
    return (
        <main>
            <h2 className={"monthlyQuestionnaireHeading"}>Monthly Questionnaire</h2>
            <SelectQuestionnaire />
        </main>
    );
};