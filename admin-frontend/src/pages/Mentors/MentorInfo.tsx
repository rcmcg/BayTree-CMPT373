import React from "react";

interface MentorInfoInterface {
  info: {
    lastName: string;
    firstName: string;
    status: string;
    role: string;
    startDate: string;
    age: number;
    gender: string;
    email: string;
    firstLanguage: string;
    otherLanguages: string;
  };
}

function MentorInfo(mentorData: MentorInfoInterface["info"]) {
  return (
    <p>
        <h3>{mentorData.lastName}, {mentorData.firstName}</h3>
        <strong>Volunteer Status:</strong> {mentorData.status} <br/>
        <strong>Volunteer Role:</strong> {mentorData.role} <br/>                 
        <strong>Start Date:</strong> {mentorData.startDate} <br/>  
        <strong>Age:</strong> {mentorData.age} <br/>  
        <strong>Gender:</strong> {mentorData.gender} <br/> 
        <strong>Email:</strong> {mentorData.email} <br/>
        <strong>Primary Language:</strong> {mentorData.firstLanguage} <br/>
        <strong>Other Languages:</strong> {mentorData.otherLanguages} <br/>
    </p>
  );
}

export default MentorInfo;