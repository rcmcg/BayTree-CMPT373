import React, {ChangeEvent, DetailedHTMLProps, SelectHTMLAttributes} from "react";
import {SessionGroupInterface, MentorInterface} from "./MentorInterfaces"
import axios, {AxiosError, AxiosResponse} from "axios";
import {backendApiURL} from "../../App";

function MentorInfo(mentorData: MentorInterface, sessionGroups: SessionGroupInterface[]) {
    function handleSessionGroupChange(event: React.ChangeEvent<HTMLSelectElement>) {
        // Get the value from the drop down
        let sessionIdSessionName: (number|string|null)[] = getSessionGroupIdSessionGroupNameFromDropdown(event);
        let selectedSessionGroupId: number | string | null = sessionIdSessionName[0]
        let selectedSessionGroupName: number | string | null = sessionIdSessionName[1]
        console.log("selectedSessionGroupId: " + selectedSessionGroupId)
        console.log("selectedSessionGroupName: " + selectedSessionGroupName)
        // Send the new value to the backend
        let boolSuccess = sendNewSessionAssociationToBackend(mentorData.viewsId, selectedSessionGroupId as number, selectedSessionGroupName as string)
    }

    function getSessionGroupIdSessionGroupNameFromDropdown(event: React.ChangeEvent<HTMLSelectElement>) {
        let text = event.target.options[event.target.selectedIndex].text
        return [parseInt(event.target.value), text]
    }

    function sendNewSessionAssociationToBackend(mentorViewsId: number, sessionGroupId: number, sessionGroupName: string) {
        let url = backendApiURL + "/user/mentors/" + mentorViewsId.toString() + "/sessiongroup";
        axios.put(url, {
            viewsSessionGroupId: sessionGroupId,
            viewsSessionGroupName: sessionGroupName
        })
            .then(function (response: AxiosResponse) {
                console.log(response)
                if (response.data != null) {
                    console.log("Successfully updated session group in Views")
                    return true
                }
            })
            .catch(function (error: AxiosError) {
                alert('Failed to save your choice of session group for this mentee. Please try again later');
                return false
            })
        return false
    }

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
                <select id={"selectSessionGroupId"} name={"sessionGroupId"} onChange={event => handleSessionGroupChange(event)}>
                    <option value={mentorData.sessionGroupId}>{mentorData.sessionGroupName}</option>
                    {sessionGroups.map(sessionGroup => <option value = {sessionGroup["viewsSessionGroupId"]}>{sessionGroup["viewsSessionGroupName"]}</option>)}
                </select>
            </div>
        </div>
    );
}

export default MentorInfo;