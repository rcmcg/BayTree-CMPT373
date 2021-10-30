import * as React from 'react';
import "../css/SessionForm.css"

import {SessionForm} from "../components/SessionForm";

export const Session = () => {
    return (
        <main>
            <h2 className={"sessionHeading"}>Mentoring Session</h2>
            <SessionForm />
        </main>
    );
};