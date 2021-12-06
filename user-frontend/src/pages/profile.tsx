// @flow
import * as React from 'react';
import "../css/dashboard/profile.css"

export const Profile = () => {
    return (
        <div style={{height: "90vh"}}>
            <h2> Profile Information</h2>

            <h4> Name </h4>
            <div className={"info-container"}>
                Arnold
            </div>
            <h4> Email </h4>
            <div className={"info-container"}>
                arnold1234@gmai.com
            </div>
            <h4> Mentor type </h4>
            <div className={"info-container"}>
                IntoSchool Mentor
            </div>

        </div>
    );
};