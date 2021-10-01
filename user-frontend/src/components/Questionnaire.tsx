import React, {Component} from 'react';
import "../css/Questionnaire.css"

class Questionnaire extends Component {
    render() {
        return (
            <div className={"questionnaire"}>

                <h5 className={"header-component"}>
                    Monthly Questionnaires
                </h5>

                <section className={"body-questionnaire"}>
                    <div className={"questionnaire-box"}>
                        <span>#7</span>
                        <span>#8</span>
                        <span>#9</span>
                        <span>#10</span>
                        <span>#11</span>
                    </div>
                    <div className={"month"}>
                        <span>July</span>
                        <span>August</span>
                        <span>September</span>
                        <span>October</span>
                        <span>November</span>
                    </div>
                </section>

            </div>
        );
    }
}

export default Questionnaire;