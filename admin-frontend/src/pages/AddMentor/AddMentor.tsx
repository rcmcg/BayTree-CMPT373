import axios from 'axios';
import React, {useState, useEffect } from 'react';
import "./Mentors.css";
import  { MentorsList } from "./UnregisteredMentors"
import {Mentor, MentorsContext} from './UsersContextProvider';

interface User {
    username: string,
    password: string,
}

function AddMentor() {

    const fetchMentors = async () => {
        await axios.get<User[]>("http://localhost:8080/user/get/all")
            .then((response) => {
                const usersDB = response.data;
                // console.log("users api:", usersDB)
                setUsers([])
                usersDB.map(user => {
                    users.push(user);
                })
            })
        console.log("users after fetch:", users)

        await axios.get<Mentor[]>("http://localhost:8080/user/get/mentors/all")
            .then((response) => {
                const mentorsDB = response.data;
                // console.log("mentors api:", mentorsDB)
                setTempMentors([]);
                mentorsDB.map((mentor:Mentor) => {
                    tempMentors.push(mentor)
                    users.map((user: User) => {
                        if (mentor.firstName === user.username){
                            tempMentors.pop();
                        }
                    })
                })
            })
        // console.log("tempMentors after fetch:", tempMentors)
        // console.log("tempMentors after filtered:", mentors)
        setMentors(tempMentors);
        // console.log("mentors after filtered:", mentors)
    }

    const [users, setUsers] = useState<User[]>([]);
    const [mentors, setMentors] = useState<Mentor[]>([]);
    const [tempMentors, setTempMentors] = useState<Mentor[]>([]);

    useEffect (() => {
        fetchMentors();
    }, []);

    const addUser = async (id: number, updatedMentor: any) => {

        // // change mentor's information
        // setMentors(mentors.map((mentor) => mentor.id === id ? updatedMentor : mentor))
        //
        // // delete mentor in the unregisted mentors list
        // setMentors(mentors.filter(mentor => mentor.id !== id))

        await axios.post<Mentor>('http://localhost:8080/user/add', {
            username: updatedMentor.firstName,
            password: updatedMentor.password
        })
        fetchMentors();
    }

  return (
    <div className='mentors'>
        <h1>List of unregistered mentors</h1>
        <MentorsContext.Provider value={{
            mentors, addUser,
            fetchMentors
        }}>
            <MentorsList />
        </MentorsContext.Provider>
    </div>
  );
}

export default AddMentor;


