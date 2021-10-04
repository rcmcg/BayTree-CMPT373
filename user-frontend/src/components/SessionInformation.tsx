import * as React from 'react';

class ClockIn extends React.Component {
    render() {
        return (
            <div>
                <label form="clockInId">Session clock in date and time</label>
                <input type="datetime-local" id="clockInId" name="clockInName" required/>
            </div>
        );
    }
}

class ClockOut extends React.Component {
    render() {
        return (
            <div>
                <label form="clockOutId">Session clock out date and time</label>
                <input type="datetime-local" id="clockOutId" name="clockOutName" required/>
            </div>
        );
    }
}

export class SessionInformation extends React.Component {
    render () {
        return (
            <main>
                <ClockIn />
                <ClockOut />
            </main>
        );
    }
}