import React, { useEffect, useState } from 'react';
import axios, {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App";

interface mentorsList {
    viewsId: number,
    firstName: string,
    lastName: string
}

// const fetchSessions  = async () => {
//     axios.get('http://localhost:8080/sessions/get/views/{id}')
//         .then((res: any) => {
//             if(res.date !== null) {
//                 attendedSessions = res.attendance;
//             }
//         })
//         .catch((err: any) => {
//             console.log(err);
//         })
// }

class SessionInfo {
    render() {
        return (
            <div> </div>
        )
    }
}

function Home() {
    const [mentors, setMentors] = useState<mentorsList[]>([]);

    const fetchMentor = async () => {

        await axios.get<mentorsList[]>('http://localhost:8080/user/get/mentors/all')
            .then((response) => {
                const mentorDB = response.data;
                setMentors([])
                mentorDB.map(mentor => {
                    mentors.push(mentor);

                })
            })
        console.log(mentors);
    }

    useEffect (() => {
        fetchMentor();
    }, []);

    return (
        <div className='home'>
            <h1>Home</h1> <br/>

            {console.log("HERE")}
            {console.log(mentors)}

            <div>
                <label form="selectMentorId"> Mentor Name </label>
                <select id={"selectMentorId"} name={"mentorId"}>
                    <option value={""}>Select a mentor</option>
                    {mentors.map(mentor => <option value = {mentor["viewsId"]}>{mentor["firstName"] + " " + mentor["lastName"]}</option>)}
                </select>
            </div>

            <br/>
        </div>
    );
}

export default Home;

