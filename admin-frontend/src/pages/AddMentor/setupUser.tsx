import React from "react";
import {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App" ;

const axios = require('axios').default;

export class setupUser extends React.Component {
    return () {
        <div>
            <h1> Set up user </h1>
            <h3> name </h3>
            <input type={"text"} id={"mentorName"} required/>
        </div>
    }
}