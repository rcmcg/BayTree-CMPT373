import * as React from 'react';

interface QuestionnaireState {
    month: number,
    year: number,
    viewsQuestionnaireId: number,
    existingQuestionnaires: string[]
}

class SelectMonth extends React.Component {
    render() {
        return (
            <div>
                <label form="selectMonth">Month </label>
                <input type="month" id="selectMonth" name="month" required/>
            </div>
        )
    }
}

class SelectYear extends React.Component{
    render() {
        return (
            <div>
                <label form="selectYear">Year </label>
                <input type="year" id="selectYear" name="year" required/>
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

export class QuestionnaireForm extends React.Component<{}, QuestionnaireState>{
    constructor(props: any) {
        super(props);
        this.state = {
            month: -1,
            year: -1,
            viewsQuestionnaireId: -1,
            existingQuestionnaires:
                ["This will contain a list of existing (year-month, viewsQuestionnaireId) pairs in our database",
                "2nd entry for testing"]
        }
    }
    render() {
        return (
            <main>
                <form>
                    <SelectMonth />
                    <SelectYear />
                    <SelectViewsQuestionnaireId />
                </form>
                <div>QuestionnaireForm</div>
            </main>

        )
    }
}