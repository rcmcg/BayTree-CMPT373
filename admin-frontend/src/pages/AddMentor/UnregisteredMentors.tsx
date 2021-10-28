import React, {useContext, useEffect, useState } from "react";
import {Modal, Button, OverlayTrigger, Tooltip} from 'react-bootstrap'
import { SingleUser } from "./SIngleUser";
import { MentorsContext } from "./UsersContextProvider";

export const MentorsList= () => {
    const {mentors} = useContext(MentorsContext);

    return (
        <div>
            <table>
                <thead>
                <tr>
                    <th> First Name</th>
                    <th> Last Name </th>
                    <th> Action</th>
                </tr>
                </thead>
                <tbody>
                {
                    mentors.map(mentor => (
                        <tr key={mentor.id}>
                            <SingleUser mentor={mentor}/>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    );
}
