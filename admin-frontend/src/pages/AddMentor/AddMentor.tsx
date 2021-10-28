import React, { useState } from 'react';
import "./Mentors.css";
import  { MentorsList } from "./UnregisteredMentors"
import {MentorsContext, mentorsContextValue } from './UsersContextProvider';

function AddMentor() {
  return (
    <div className='mentors'>
        <h1>List of unregistered mentors</h1>
        <MentorsContext.Provider value={mentorsContextValue}>
            <MentorsList />
        </MentorsContext.Provider>
    </div>
  );
}

export default AddMentor;