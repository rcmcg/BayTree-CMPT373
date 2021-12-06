import * as React from 'react';
import "../css/dashboard/profile.css"
import {useState} from "react";
import axios from "axios";
import {FC} from 'react'


function Profile() {
    const [firstName, setFirstName] = useState("");
    const [email, setEmail] = useState("");
    const [mentorType, setMentorType] = useState("");

    axios.get(`http://localhost:8080/user/get/mentors/${localStorage.getItem("username")}`)
            .then(function (response: any) {
                const parsedResponse = JSON.parse(response);
                setFirstName(parsedResponse.firstName + parsedResponse.lastName);
                setEmail(parsedResponse.email);
                setMentorType(parsedResponse.role);
            })
            .catch(function (error: any) {
                console.log(error);
        })

    return (
        <div style={{height: "90vh"}}>
            <h2> Profile Information</h2>

            <h4> Name </h4>
            <div className={"info-container"}>
                {firstName}
            </div>
            <h4> Email </h4>
            <div className={"info-container"}>
                {email}
            </div>
            <h4> Mentor type </h4>
            <div className={"info-container"}>
                {mentorType}
            </div>

        </div>
    );
}

export default Profile;