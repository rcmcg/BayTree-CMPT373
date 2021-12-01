import React, {useContext, useEffect, useState } from "react";
import axios, {AxiosError, AxiosResponse} from "axios";

interface Props {
    mentors: any
}

export const MentorsMenu:  React.FC<Props> = ({mentors})=> {

    let [attendedSessions, setAttendedSessions] = useState<number>();

    const handleSelectMentor = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.value;
        fetchSessions(value);
    }
    const fetchSessions  = async (id: String) => {
        axios.get('http://localhost:8080/sessions/get/views/'+ id)
            .then((res: any) => {
                if(res.date !== null) {
                    console.log(res.data)
                    console.log("numSessions: " + res.data.length);
                    setAttendedSessions(res.data.length);
                    // console.log("Attended Sessions: " +attendedSessions);


                }
            })
            .catch((err: any) => {
                console.log(err);
            })
    }

    const getRemainingSessions =() => {
        // mentoring session held once per week
        let currentWeekNumber = require('current-week-number');
        let current = currentWeekNumber();
        const TOTAL_WEEKS_OF_A_YEAR = 52;
        let remainingSessions = TOTAL_WEEKS_OF_A_YEAR - current;
        console.log("current " +current);
        return remainingSessions;
    }
    let remainingSessions = getRemainingSessions();

    // console.log(mentors);
    return (
        <div>
            <label form="selectMentorId"> Mentor Name  </label>
            <select id={"selectMentorId"} name={"mentorId"}
                    onChange={handleSelectMentor}>

                <option selected disabled>Select a mentor</option>

                {mentors.map((mentor: { [x: string]: string; }) =>
                    <option value = {mentor["viewsId"]} >
                        {mentor["viewsId"]+" "+mentor["firstName"] + " " + mentor["lastName"]}
                    </option>)}
            </select>

            <p>Attended sessions : {attendedSessions}</p>
            <p>Remaining sessions : {remainingSessions}</p>
        </div>

    );
}
