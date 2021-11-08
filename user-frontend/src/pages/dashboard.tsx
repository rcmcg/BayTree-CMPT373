// @flow
import * as React from 'react';
import Notifications from "../components/dashboard/Notifications";
import Questionnaire from "../components/dashboard/Questionnaire";
import HistoricalRecord from "../components/dashboard/HistoricalRecord";
import ClockIn from '../components/dashboard/ClockIn';

export const Dashboard = () => {

    return (
        <main>

            <section className={"wrapper"}>

                <div className={"left-container"}>
                    <ClockIn />
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