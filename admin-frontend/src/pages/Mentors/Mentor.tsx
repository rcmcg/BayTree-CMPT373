import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';

import MentorInfo from './MentorInfo';
import MentorSession from './MentorSessions';
import MentorQuestionnaire from './MentorQuestionnaires';
import { useLocation } from 'react-router-dom';
import { MentorInterface, MentorQuestionnaireInterface, MentorSessionInterface, emptySession, emptyQuestionnaire } from './MentorInterface';
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
            const sessionData = await getSessions()
            setSessions(sessionData);
        } 
        fetchSessions()
    }, []);

    const [questionnaires, setQuestionnaires] = useState<MentorQuestionnaireInterface[]> ([emptyQuestionnaire]);

    const getQuestionnaires = async() => {
        let url = backendApiURL + "/questionnaires/get/views/" + mentorState.viewsId;
        const response = await axios.get<MentorQuestionnaireInterface[]>(url);
        return response.data;
    }

    useEffect(() => {
        const fetchQuestionnaires = async () => {
            const questionnaireData = await getQuestionnaires()
            setQuestionnaires(questionnaireData);
        } 
        fetchQuestionnaires()
    }, []);
    
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
                    {questionnaires.map(MentorQuestionnaire)}
                </Tab>
            </Tabs>
        </div>
    )
}

export default Mentor;