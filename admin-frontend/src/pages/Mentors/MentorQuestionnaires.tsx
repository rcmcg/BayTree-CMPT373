import assert from "assert";
import React from "react";

import { MentorQuestionnaireInterface} from "./MentorInterface";

function loadAnswers(questionnaireData: MentorQuestionnaireInterface) {
    var result = [];
    if (questionnaireData.questions.length !== questionnaireData.answers.length) {
        return [<strong>Invalid questionnaire. Questionnaire could not be constructed because the number of questions does not match the number of answers.</strong>];
    }
    
    const length = questionnaireData.questions.length;
    for(var i = 0; i < length; i++) {
        result.push(
            <div>
                &nbsp; <strong>{i + 1}. {questionnaireData.questions[i]}:</strong> {questionnaireData.answers[i]} <br/>
            </div>
        )
    }
    return result;
}

function MentorQuestionnaire(questionnaireData: MentorQuestionnaireInterface) {
    const month = new Date(questionnaireData.date).toLocaleString("en-us", { month: 'long' , year: 'numeric'})
    return(
        <div>
            <h3>{questionnaireData.questionnaireName}</h3>
            <strong>Month:</strong> {month} <br/>
            {loadAnswers(questionnaireData)}
        </div>
    )
}

export default MentorQuestionnaire;