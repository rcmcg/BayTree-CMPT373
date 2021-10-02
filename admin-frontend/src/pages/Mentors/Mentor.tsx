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
                                    <div key={sessionKey}>
                                        {session.date +
                                        ", " +
                                        session.mentee +
                                        ", " +
                                        session.notes}
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
                                    <div key={questionnaireKey}>
                                        {questionnaire.month}
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
                        <div key={key}>
                        {data.id +
                            " , " +
                            data.name}
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