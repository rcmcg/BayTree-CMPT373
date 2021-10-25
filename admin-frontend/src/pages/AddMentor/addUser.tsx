import React, { useState } from "react";
import {AxiosError, AxiosResponse} from "axios";
import {UserState as Props} from "../AddMentor/UnregisteredMentors"
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App" ;

const axios = require('axios').default;

interface IProps {
    setUser: React.Dispatch<React.SetStateAction<Props["unregistedUsers"]>>
    unregistedUsers: Props["unregistedUsers"]
}

export const AddUser: React.FC<IProps> = ({setUser, unregistedUsers}) => {

    const [input,setInput] = useState({
        id: "",
        firstName: "",
        lastName: "",
        password: ""
    })

    return (
        <div>
            <h1> Set up user </h1>
            <h3> name </h3>
            <input type={"text"} id={"mentorName"} required/>
        </div>
    )
}