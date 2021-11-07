import React from "react";

interface MentorInfoInterface {
  info: {
    lastName: string;
    firstName: string;
    status: string;
    role: string;
    startDate: string;
    endDate: string;
    age: number;
    email: string;
    phoneNumber: string;
  };
}

function MentorInfo(mentorData: MentorInfoInterface["info"]) {
  return (
    <p>
        <h3>{mentorData.lastName}, {mentorData.firstName}</h3>
        <strong>Volunteer Status:</strong> {mentorData.status} <br/>
        <strong>Volunteer Role:</strong> {mentorData.role} <br/>                 
        <strong>Start Date:</strong> {mentorData.startDate} <br/>  
        <strong>End Date:</strong> {mentorData.endDate} <br/> 
        <strong>Age:</strong> {mentorData.age} <br/>  
        <strong>Email:</strong> {mentorData.email} <br/>
        <strong>Phone Number:</strong> {mentorData.phoneNumber} <br/>
    </p>
  );
}

export default MentorInfo;