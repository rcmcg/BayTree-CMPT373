// @flow
import * as React from 'react';
import Notifications from "../components/Notifications";
import Questionnaire from "../components/Questionnaire";
import HistoricalRecord from "../components/HistoricalRecord";

export const Dashboard = () => {

    return (
        <main>
            <section className={"wrapper"}>
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