import axios, {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App";
import {useEffect, useState } from "react";
import { MentorsMenu } from "./MentorsMenu";

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

interface Mentor {
    id: number;
    firstName: String,
    lastName: String
}

class SessionInfo {
    render() {
        return (
            <div> </div>
        )
    }
}

function Home() {
    const [mentors, setMentors] = useState<Mentor[]>([]);
    const [tempMentors, setTempMentors] = useState<Mentor[]>([]);

    const fetchMentor = async () => {
        await axios.get<Mentor[]>('http://localhost:8080/user/get/mentors/all')
            .then((response) => {
                const mentorDB = response.data;
                setMentors([])
                mentorDB.map(mentor => {
                    tempMentors.push(mentor);
                })
            })
        setMentors(tempMentors);
        console.log(mentors);
    }

    useEffect (() => {
        fetchMentor();
    }, []);

    return (

        <div className='home'>
            <h1>Home</h1> <br/>
                <MentorsMenu mentors={mentors}/>
            <br/>
        </div>
    );
}

export default Home;

