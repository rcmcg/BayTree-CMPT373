import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';
import './styles/Mentors.css'

import mentor from '../../assets/dummy-data/mentor28/Mentor.json';
import sessionsData from '../../assets/dummy-data/mentor28/SessionsCombined.json';
import questionnairesData from '../../assets/dummy-data/mentor28/QuestionnairesCombined.json';


function Mentor() {
    return (
        <div className='mentor'>
            <h1>Mentor</h1>
            <Tabs defaultActiveKey="sessions" className = 'tab'>
                <Tab eventKey="sessions" title="Sessions">
                    {Object.values(sessionsData).map((sessions) => {
                        return(
                            Object.values(sessions).map((session) => {
                                const date = new Date(session.StartDate);
                                return(
                                    <div>
                                        <h3>{date.toDateString()}</h3>
                                        <h4>{session.Title}</h4>
                                        <p><strong>Start Time: </strong> {date.toLocaleTimeString("en-US")}</p>
                                        <p><strong>Duration: </strong> {session.Duration}</p>
                                        <p><strong>Attendance: </strong> {session.Status}</p>
                                        <p><strong>Notes: </strong>
                                        {Object.values(session.notes).map((notes) => {
                                            return (
                                                <div>{notes.Note}</div>
                                            )
                                        })}
                                        </p>
                                    </div>
                                )
                            })
                        )
                    })}
                </Tab>
                <Tab eventKey="questionnaire" title="Questionnaire">
                    {Object.values(questionnairesData).map((questionnaire) => {
                        return(
                            <div>
                                <h3>{questionnaire.Questionnaire}</h3>
                                {Object.values(questionnaire.Questions).map((questions) =>{
                                    return (
                                        <p>{questions.Question}: {questions.Answer}</p>
                                    )
                                })}
                            </div>
                        )
                    })}
                </Tab>
                <Tab eventKey="info" title="Personal Information">
                    <div className = "info">
                        <h3>{mentor.Surname}, {mentor.Forename}</h3>
                        <p><strong>Volunteer Status:</strong> {mentor.VolunteerStatus_V_1} </p>
                        <p><strong>Volunteer Role:</strong> {mentor.Volunteerrole_V_34.replaceAll("|", ", ")} </p>                 
                        <p><strong>Start Date:</strong> {mentor.Startdate_V_37} </p>  
                        <p><strong>Age:</strong> {Math.floor(Number(mentor.Age)/365)} </p>  
                        <p><strong>Gender:</strong> {mentor.Gender} </p> 
                        <p><strong>Email:</strong> {mentor.Email} </p>
                        <p><strong>Primary Language:</strong> {mentor.Whatisyourfirstlanguage_V_19} </p>
                        <p><strong>Other Languages:</strong> {mentor.Anyotherlanguages_V_28.replaceAll("|", ", ")} </p>
                    </div>
                </Tab>
            </Tabs>
        </div>
    )
}

export default Mentor;