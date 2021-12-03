import React from "react";
import {SessionGroupInterface, MentorInterface, VolunteeringRoleInterface} from "./MentorInterfaces"
import axios, {AxiosError, AxiosResponse} from "axios";
import {backendApiURL} from "../../App";

function MentorInfo(mentorData: MentorInterface, sessionGroups: SessionGroupInterface[], volunteeringRoles: VolunteeringRoleInterface[]) {
    function handleSessionGroupChange(event: React.ChangeEvent<HTMLSelectElement>) {
        let sessionIdSessionName: (number|string|null)[] = getValueIntNameFromDropdown(event);
        let selectedSessionGroupId: number | string | null = sessionIdSessionName[0]
        let selectedSessionGroupName: number | string | null = sessionIdSessionName[1]
        sendNewSessionAssociationToBackend(mentorData.viewsId, selectedSessionGroupId as number, selectedSessionGroupName as string)
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
                }
            })
            .catch(function (error: AxiosError) {
                alert('Failed to save your choice of session group for this mentor. Please try again later');
                console.log(error)
            })
    }

    function handleVolunteeringRoleChange(event: React.ChangeEvent<HTMLSelectElement>) {
        let volunteerRoleName: number | string | null = getValueStringNameFromDropdown(event)[0];
        sendNewVolunteerRoleToBackend(mentorData.viewsId, volunteerRoleName as string)
    }

    function sendNewVolunteerRoleToBackend(mentorId: number, volunteerRoleName: string) {
        let url = backendApiURL + "/user/mentors/" + mentorId + "/volunteeringrole"
        axios.put(url, {volunteeringRoleName: volunteerRoleName})
            .then(function (response: AxiosResponse) {
                console.log(response)
                if (response.data != null) {
                    console.log("Successfully update volunteering role in Views")
                }
            })
            .catch(function (error: AxiosError) {
                alert('Failed to save your choice of volunteering role for this mentor. Please try again later');
                console.log(error)
            })
    }

    function getValueIntNameFromDropdown(event: React.ChangeEvent<HTMLSelectElement>) {
        let text = event.target.options[event.target.selectedIndex].text
        return [parseInt(event.target.value), text]
    }

    function getValueStringNameFromDropdown(event: React.ChangeEvent<HTMLSelectElement>) {
        let text = event.target.options[event.target.selectedIndex].text
        return [event.target.value, text]
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
            <div>
                <label form={"selectSessionGroupId"}><strong>Views session group: </strong></label>
                <select id={"selectSessionGroupId"} name={"sessionGroupId"} onChange={event => handleSessionGroupChange(event)}>
                    <option value={mentorData.sessionGroupId}>{mentorData.sessionGroupName}</option>
                    {sessionGroups.map(sessionGroup =>
                        <option value = {sessionGroup["viewsSessionGroupId"]}>
                            {sessionGroup["viewsSessionGroupName"]}
                        </option>)}
                </select>
            </div>
            <div>
                <label form={"selectVolunteeringRoleId"}><strong>Volunteering role: </strong></label>
                <select id={"selectVolunteeringRoleId"} name={"volunteeringRoleId"} onChange={event => handleVolunteeringRoleChange(event)}>
                    <option value={mentorData.volunteeringRoleName}>{mentorData.volunteeringRoleName}</option>
                    {volunteeringRoles.map(volunteeringRole =>
                        <option value = {volunteeringRole["volunteeringRoleName"]}>
                            {volunteeringRole["volunteeringRoleName"]}
                        </option>)}
                </select>
            </div>
        </div>
    );
}

export default MentorInfo;