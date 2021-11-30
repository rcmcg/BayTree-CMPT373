import React from "react";
import {SessionGroupInterface, MentorInterface} from "./MentorInterfaces"

function MentorInfo(mentorData: MentorInterface, sessionGroups: SessionGroupInterface[]) {
  return (
    <div>
        <h3>{mentorData.firstName} {mentorData.lastName}</h3>
        <strong>Volunteer Status:</strong> {mentorData.status} <br/>
        <strong>Volunteer Role:</strong> {mentorData.role} <br/>                 
        <strong>Start Date:</strong> {mentorData.startDate} <br/>  
        <strong>End Date:</strong> {mentorData.endDate} <br/> 
        <strong>Email:</strong> {mentorData.email} <br/>
        <strong>Phone Number:</strong> {mentorData.phoneNumber} <br/>
        <strong>Views session group (replace with actual name):</strong> ID (for testing): {mentorData.sessionGroupId} Name: {mentorData.sessionGroupName} <br/>
        <div>
            <label form={"selectSessionGroupId"}><strong>Views session group (replace with actual name) (ID for testing): {mentorData.sessionGroupId}</strong></label>
            <select id={"selectSessionGroupId"} name={"sessionGroupId"}>
                <option value={mentorData.sessionGroupId}>{mentorData.sessionGroupName}</option>
                {sessionGroups.map(sessionGroup => <option value = {sessionGroup["viewsSessionGroupId"]}>{sessionGroup["viewsSessionGroupName"]}</option>)}
            </select>
        </div>
    </div>
  );
}

export default MentorInfo;