import * as React from "react";

export interface Mentor {
    id: number,
    firstName: string,
    lastName: string, role: string, email:string,
    password: string, phone: number
}

//PostsContextData
export interface UnregisteredMentors {
    mentors: Mentor[] ;
    addUser: (id: any, updatedMentor: any) => void;
}

//postsContextDefaultValue
export const mentorsContextValue: UnregisteredMentors = {
    mentors: [
        {id: 1, firstName: "user1", lastName: "Smith", password: "default password",
            role: "Financial Advisor", email: "user1@gmail.com", phone: 1231233231},
        {id: 2, firstName: "user2", lastName: "Brown", password: "default password",
            role: "Broker", email: "user2@gmail.com", phone: 1231233231},
        {id: 3, firstName: "user3", lastName: "Tremblay", password: "default password",
            role: "Attorney", email: "user3@gmail.com", phone: 1231233231},
        {id: 4, firstName: "user4", lastName: "Martin", password: "default password",
            role: "Babysister", email: "user4@gmail.com", phone: 1231233231},
        {id: 5, firstName: "user5", lastName: "Smith", password: "default password",
            role: "Financial Advisor", email: "user5@gmail.com", phone: 1231233231}
    ],
    addUser: (id: any, updatedMentor: any) => null,
}

export const MentorsContext = React.createContext<UnregisteredMentors>(mentorsContextValue);
