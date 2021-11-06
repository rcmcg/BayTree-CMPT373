import * as React from "react";

export interface Mentor {
    id: number,
    firstName: string,
    lastName: string, role: string, email:string,
    password: string, phone: number
}
//
// export interface User {
//     username: string,
// }

export interface UnregisteredMentors {
    mentors: Mentor[];
    addUser: (id: any, updatedMentor: any) => void;
    fetchMentors: () => void;
    // fetchUsers: () => void;
}


export const mentorsContextDefaultValue: UnregisteredMentors = {
    mentors: [],
    addUser: (id: any, updatedMentor: any) => null,
    fetchMentors: () => null,
    // fetchUsers: () => null
}

export const MentorsContext = React.createContext<UnregisteredMentors>(mentorsContextDefaultValue);
