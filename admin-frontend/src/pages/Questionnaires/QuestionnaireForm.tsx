import * as React from 'react';

interface QuestionnaireState {
    monthInt: number,
    yearInt: number,
    viewsQuestionnaireId: number
}

export class QuestionnaireForm extends React.Component<{}, QuestionnaireState>{
    constructor(props: any) {
        super(props);
        this.state = {
            monthInt: -1,
            yearInt: -1,
            viewsQuestionnaireId: -1
        }
    }
    render() {
        return (
            <div>QuestionnaireForm</div>
        )
    }
}