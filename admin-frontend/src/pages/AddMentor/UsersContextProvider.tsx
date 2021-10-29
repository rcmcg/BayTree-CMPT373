import * as React from "react";

export interface Mentor {
    id: number,
    firstName: string,
    lastName: string,
    password: string,
}

//PostsContextData
export interface UnregisteredMentors {
    mentors: Mentor[] ;
    addUser: (id: any, updatedMentor: any) => void;
}

//postsContextDefaultValue
export const mentorsContextValue: UnregisteredMentors = {
    mentors: [
        {id: 1, firstName: "user1", lastName: "Smith", password: "user1"},
        {id: 2, firstName: "user2", lastName: "Brown", password: "user2"},
        {id: 3, firstName: "user3", lastName: "Tremblay", password: "user3"},
        {id: 4, firstName: "user4", lastName: "Martin", password: "user4"},
        {id: 5, firstName: "user5", lastName: "Smith", password: "user5"}
    ],
    addUser: (id: any, updatedMentor: any) => null,
}

export const MentorsContext = React.createContext<UnregisteredMentors>(mentorsContextValue);
