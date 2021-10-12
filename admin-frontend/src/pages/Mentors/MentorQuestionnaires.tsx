import assert from "assert";
import React from "react";

interface MentorQuestionnaireInterface {
  questionnaire: {
    date: string;
    questionnaire: string;
    questions: string[];
    answers: string[];
  };
}

function loadAnswers(questionnaireData: MentorQuestionnaireInterface["questionnaire"]) {
    assert(questionnaireData.questions.length === questionnaireData.answers.length)
    var result = [];
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

function MentorQuestionnaire(questionnaireData: MentorQuestionnaireInterface["questionnaire"]) {
    const month = new Date(questionnaireData.date).toLocaleString("en-us", { month: 'long' })
    return(
        <div>
            <h3>{questionnaireData.questionnaire}</h3>
            <p>
                <strong>Month:</strong> {month} <br/>
                {loadAnswers(questionnaireData)}
            </p>
        </div>
    )
}

export default MentorQuestionnaire;