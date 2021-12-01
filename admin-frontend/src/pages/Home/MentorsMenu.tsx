import React, {useContext, useEffect } from "react";

interface Props {
    mentors: any
}

export const MentorsMenu:  React.FC<Props> = ({mentors})=> {

    return (
        <div>
            <label form="selectMentorId"> Mentor Name </label>
            <select id={"selectMentorId"} name={"mentorId"}>
                <option value={""}>Select a mentor</option>
                {mentors.map((mentor: { [x: string]: string; }) => <option value = {mentor["id"]}>{mentor["firstName"] + " " + mentor["lastName"]}</option>)}
            </select>
        </div>

    );
}
