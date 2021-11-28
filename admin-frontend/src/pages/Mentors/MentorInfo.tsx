import React from "react";
import {MentorInterface} from "./MentorInterfaces"

function MentorInfo(mentorData: MentorInterface) {
  return (
    <div>
        <h3>{mentorData.firstName} {mentorData.lastName}</h3>
        <strong>Volunteer Status:</strong> {mentorData.status} <br/>
        <strong>Volunteer Role:</strong> {mentorData.role} <br/>                 
        <strong>Start Date:</strong> {mentorData.startDate} <br/>  
        <strong>End Date:</strong> {mentorData.endDate} <br/> 
        <strong>Email:</strong> {mentorData.email} <br/>
        <strong>Phone Number:</strong> {mentorData.phoneNumber} <br/>
        <strong>Session Group ID (replace with actual name):</strong> {mentorData.sessionGroupId} <br/>
    </div>
  );
}

export default MentorInfo;