// @flow
import * as React from 'react';
import Notifications from "../components/Notifications";
import Questionnaire from "../components/Questionnaire";

export const Dashboard = () => {

    return (
        <main>
            <div style={{height: "10vh"}}/>

            <h1>Dashboard</h1>
            <h2>Session information</h2>
            <h3>Test</h3>

            <section className={"bottom-wrapper"}>
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