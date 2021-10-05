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
                <div className={"left"}>
                    <div> Clock in</div>
                </div>
                <div className={"right"}>
                    <HistoricalRecord />
                </div>
            </section>

            <section className={"wrapper"}>
                <div className={"left"}>
                    <Notifications />
                </div>
                <div className={"right"}>
                    <Questionnaire />
                </div>
            </section>

        </main>
    );
};