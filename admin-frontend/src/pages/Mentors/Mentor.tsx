import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';
import './styles/Mentors.css'

// import Tabs from './components/Tabs/Tabs';
// import Tab from './components/Tabs/Tab';
import { mentorData } from '../../assets/dummy-data/DummyMentors'; 
import { mentor1Data } from '../../assets/dummy-data/DummyMentor1'; 
function Mentor() {
  return (
    <div className='mentor'>
        <h1>Mentor</h1>
    
        <Tabs defaultActiveKey="sessions" className = 'tab'>
            <Tab eventKey="sessions" title="Sessions">
                {mentor1Data.map((data, key) => {
                    return (
                        <div key={key}>
                            {data.sessions.map((session, sessionKey) => {
                                return (
                                    <div key={sessionKey}>{
                                        <div>
                                            <h2>{session.date}</h2>
                                            <p>Mentee: {session.mentee}</p>
                                            <p>Notes: {session.notes}</p>
                                        </div>}
                                    </div>
                                )
                            })}
                        </div>
                    );
                })}
            </Tab>
            <Tab eventKey="questionnaire" title="Questionnaire">
                {mentor1Data.map((data, key) => {
                    return (
                        <div key={key}>
                            {data.questionnaires.map((questionnaire, questionnaireKey) => {
                                return (
                                    <div key={questionnaireKey}>{
                                        <div>
                                            <h2>{questionnaire.month}</h2>
                                            <h3>Mentee: {questionnaire.mentee}</h3>
                                            <p><strong>Q1: {questionnaire.q1}</strong> {questionnaire.a1}</p>
                                            <p><strong>Q2: {questionnaire.q2}</strong> {questionnaire.a2}</p>
                                        </div>}
                                    </div>
                                )
                            })}
                        </div>
                    );
                })}
            </Tab>
            <Tab eventKey="info" title="Personal Information">
                {mentor1Data.map((data, key) => {
                    return (
                        <div key={key}>{
                            <div>
                                <h2>{data.name}</h2>
                                <p> ID: {data.id} </p>                            
                            </div>}
                        </div>
                    );
                })}
            </Tab>
        </Tabs>

      {/* <Tabs>
        <Tab title = "Sessions">

        </Tab>
        <Tab title = "Questionnaires">

        </Tab>
        <Tab title = "Personal Information">

        </Tab>
      </Tabs> */}
    </div>
  );
}

export default Mentor;