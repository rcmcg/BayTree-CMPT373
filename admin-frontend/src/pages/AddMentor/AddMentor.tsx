import React, {useCallback, useState } from 'react';
import "./Mentors.css";
import  { MentorsList } from "./UnregisteredMentors"
import {Mentor, MentorsContext, mentorsContextValue } from './UsersContextProvider';

function AddMentor() {

    const [mentors, setMentors] = useState<Mentor[]>([
        {id: 1, firstName: "user1", lastName: "Smith", password: "user1"},
        {id: 2, firstName: "user2", lastName: "Brown", password: "user2"},
        {id: 3, firstName: "user3", lastName: "Tremblay", password: "user3"},
        {id: 4, firstName: "user4", lastName: "Martin", password: "user4"},
        {id: 5, firstName: "user5", lastName: "Smith", password: "user5"}
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