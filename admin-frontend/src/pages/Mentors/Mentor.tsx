import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';

import MentorInfo from './MentorInfo';
import MentorSession from './MentorSessions';
import MentorQuestionnaire from './MentorQuestionnaires';
import { useParams } from 'react-router-dom';
import { MentorInterface, MentorQuestionnaireInterface, MentorSessionInterface, emptySession, emptyQuestionnaire, emptyMentor } from './MentorInterface';
import { backendApiURL } from "../../App";
import axios from 'axios';

function Mentor() {
    console.log(useParams())
    const { id } = useParams() as { 
        id: string;
      }

    //TODO fix bug where clicking on navbar crashes the page
    const [mentor, setMentor] = useState<MentorInterface> (emptyMentor);

    const getMentor = async() => {
      let url = backendApiURL + "/user/get/mentors/" + id;
      const response = await axios.get<MentorInterface>(url);
      return response.data;
    }
  
    useEffect(() => {
      const fetchMentors = async () => {
        const mentorData = await getMentor()
        setMentor(mentorData);
      } 
      fetchMentors()
    }, []);

    const [sessions, setSessions] = useState<MentorSessionInterface[]> ([emptySession]);

    const getSessions = async() => {
        let url = backendApiURL + "/sessions/get/views/" + id;
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
        let url = backendApiURL + "/questionnaires/get/views/" + id;
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
                    {MentorInfo(mentor)}
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

