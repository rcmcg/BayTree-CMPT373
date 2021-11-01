import React, {useCallback, useState } from 'react';
import "./Mentors.css";
import  { MentorsList } from "./UnregisteredMentors"
import {Mentor, MentorsContext, mentorsContextValue } from './UsersContextProvider';

function AddMentor() {

    const [mentors, setMentors] = useState<Mentor[]>([
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
    ]);

    const addUser = (id: number, updatedMentor: any) => {
        // change mentor's information
        setMentors(mentors.map((mentor) => mentor.id === id ? updatedMentor : mentor))

        // delete mentor in the unregisted mentors list
        setMentors(mentors.filter(mentor => mentor.id !== id))
    }

  return (
    <div className='mentors'>
        <h1>List of unregistered mentors</h1>
        <MentorsContext.Provider value={{
            mentors, addUser
        }}>
            <MentorsList />
        </MentorsContext.Provider>
    </div>
  );
}

export default AddMentor;