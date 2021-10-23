import React from 'react';
import "./Mentors.css";
import data from "./mockdata.json";
import UnregisteredMentors from "./UnregisteredMentors"

function AddMentor() {

  return (
    <div className='mentors'>
        <h1>List of unregistered mentors</h1>
        <table>
            <thead>
                <tr>
                    <th> First Name</th>
                    <th> Last Name </th>
                    <th> </th>
                </tr>
            </thead>
            <tbody> {data.map(UnregisteredMentors)} </tbody>
        </table>
    </div>
  );
}

export default AddMentor;