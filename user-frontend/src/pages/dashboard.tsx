// @flow
import * as React from 'react';
import Notifications from "../components/Notifications";
import Questionnaire from "../components/Questionnaire";
import HistoricalRecord from "../components/HistoricalRecord";

export const Dashboard = () => {

    return (
        <main>
            <div style={{height: "20vh"}}/>

            <section className={"wrapper"}>
                <div className={"left-container"}>
                    <div> Clock in</div>
                </div>
                <div className={"right-container"}>
                    <HistoricalRecord />
                </div>
            </section>

            <section className={"wrapper"}>
                <div className={"left-container"}>
                    <Notifications />
                </div>
                <div className={"right-container"}>
                    <Questionnaire />
                </div>
            </section>

        </main>
    );
};