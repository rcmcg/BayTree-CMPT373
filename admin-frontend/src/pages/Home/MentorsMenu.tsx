import React, {useContext, useEffect, useState } from "react";
import axios, {AxiosError, AxiosResponse} from "axios";

interface Props {
    mentors: any
}

let attendedSessions = 0;

const fetchSessions  = async (id: String) => {
    axios.get('http://localhost:8080/sessions/get/views/'+ id)
        .then((res: any) => {
            if(res.date !== null) {
                attendedSessions = res.data.length;
                console.log(res.data);
                console.log(attendedSessions);
            }
        })
        .catch((err: any) => {
            console.log(err);
        })
}



export const MentorsMenu:  React.FC<Props> = ({mentors})=> {

    const handleSelectMentor = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.value;
        fetchSessions(value);
    }

    // console.log(mentors);
    return (
        <div>
            <label form="selectMentorId"> Mentor Name </label>

            <select id={"selectMentorId"} name={"mentorId"}
                    onChange={handleSelectMentor}>

                <option selected disabled>Select a mentor</option>

                {mentors.map((mentor: { [x: string]: string; }) =>
                    <option value = {mentor["viewsId"]} >
                        {mentor["firstName"] + " " + mentor["lastName"]}
                    </option>)}

            </select>
        </div>

    );
}
