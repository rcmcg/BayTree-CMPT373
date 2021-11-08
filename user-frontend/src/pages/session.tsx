import * as React from 'react';
import "../css/dashboard/SessionForm.css"

import {SessionForm} from "../components/dashboard/SessionForm";

export const Session = () => {
    return (
        <main>
            <h2 className={"sessionHeading"}>Mentoring Session</h2>
            <SessionForm />
        </main>
    );
};