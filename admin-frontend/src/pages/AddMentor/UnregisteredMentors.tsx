import React from "react";
import {Link} from "react-router-dom";
import { useHistory } from "react-router";
import {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../../App" ;

const axios = require('axios').default;

export interface  UserState {
    unregistedUsers: {
        id: number;
        firstName: string;
        lastName: string;
        password: string;
    };
}

function UnregisteredMentors(user: UserState["unregistedUsers"] ) {

    return(
        <tr>
            <td>{user.firstName}</td>
            <td>{user.lastName}</td>

            <td>
                <Link to={'/setupUser'}>
                    <div className={"post-bttn"}>
                        Add
                    </div>
                </Link>
            </td>
        </tr>
    )
}

export default UnregisteredMentors;