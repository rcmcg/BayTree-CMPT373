import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';

import MentorInfo from './MentorInfo';
import MentorSession from './MentorSessions';
import MentorQuestionnaire from './MentorQuestionnaires';
import { useParams } from 'react-router-dom';
import {
    MentorInterface,
    SessionGroupInterface,
    MentorQuestionnaireInterface,
    MentorSessionInterface,
    emptySession,
    emptySessionGroup,
    emptyQuestionnaire,
    emptyMentor,
    emptyVolunteeringRole, VolunteeringRoleInterface
} from './MentorInterfaces';
import { backendApiURL } from "../../App";
import axios from 'axios';

function Mentor() {
    const { id } = useParams() as { 
        id: string;
      }

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

    const [sessionGroups, setSessionGroups] = useState<SessionGroupInterface[]>([emptySessionGroup])

    useEffect(() => {
        const fetchSessionGroups = async () => {
            const allSessionGroups = await getSessionGroups()
            setSessionGroups(allSessionGroups)
        }
        fetchSessionGroups()
    }, [])

    const getSessionGroups = async() => {
        let url = backendApiURL + "/sessiongroups/get";
        const response = await axios.get<SessionGroupInterface[]>(url);
        return response.data;
    }

    const [volunteeringRoles, setVolunteeringRoles] = useState<VolunteeringRoleInterface[]>([emptyVolunteeringRole])

    const getVolunteeringRoles = async() => {
        let url = backendApiURL + "/sessiongroups/volunteeringroles"
        const response = await axios.get<VolunteeringRoleInterface[]>(url)
        return response.data
    }

    useEffect(() => {
        const fetchVolunteeringRoles = async () => {
            const volunteeringData = await getVolunteeringRoles()
            setVolunteeringRoles(volunteeringData)
        }
        fetchVolunteeringRoles()
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
                    {MentorInfo(mentor, sessionGroups, volunteeringRoles)}
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

