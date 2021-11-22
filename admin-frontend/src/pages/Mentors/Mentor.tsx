import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';

import questionnairesData from '../../assets/dummy-data/mentor28/QuestionnairesPruned.json';

import MentorInfo from './MentorInfo';
import MentorSession from './MentorSessions';
import MentorQuestionnaire from './MentorQuestionnaires';
import { useLocation } from 'react-router-dom';
import { MentorInterface, MentorQuestionnaireInterface, MentorSessionInterface, emptySession } from './MentorInterface';
import { backendApiURL } from "../../App";
import axios from 'axios';

function Mentor() {
    //TODO fix bug where clicking on navbar crashes the page
    const mentorState = useLocation().state as MentorInterface;

    const [sessions, setSessions] = useState<MentorSessionInterface[]> ([emptySession]);

    const getSessions = async() => {
        let url = backendApiURL + "/sessions/get/views/" + mentorState.viewsId;
        const response = await axios.get<MentorSessionInterface[]>(url);
        return response.data;
    }

    useEffect(() => {
        const fetchSessions = async () => {
            const sessionsData = await getSessions()
            setSessions(sessionsData);
        } 
        fetchSessions()
    });
    
    return (
        <div className='mentor'>
            <h1>Mentor Overview</h1>
            <Tabs defaultActiveKey="info" className = 'tab'>
                <Tab eventKey="info" title="Personal Information">
                    {MentorInfo(mentorState)}
                </Tab>

                <Tab eventKey="sessions" title="Sessions">
                    {sessions.map(MentorSession)}
                </Tab>

                <Tab eventKey="questionnaire" title="Questionnaires">
                    {questionnairesData.map(MentorQuestionnaire)}
                </Tab>
            </Tabs>
        </div>
    )
}

export default Mentor;