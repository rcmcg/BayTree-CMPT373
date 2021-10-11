import React, { useState } from "react";
import "./Styles/Mentors.css";
import data from "./mockdata.json";
import MentorRow from "./MentorRow";

// May be necessary later, do not remove yet
// interface MentorStateInterface {
//   mentor: {
//     id: number;
//     username: string;
//     name: string;
//     age: number;
//     program: string;
//     startDate: string;
//     dailySessionsOutstanding: number;
//     monthlyReportsOutstanding: number;
//   }[];
// }

function Mentors() {
  // const [mentors, setMentors] = useState<MentorStateInterface["mentor"]>(data);

  return (
    <div className="mentors">
      <h1>Mentors</h1>
      <table>
        <thead>
          <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Age</th>
            <th>Program</th>
            <th>Start Date</th>
            <th>Daily Sessions Outstanding</th>
            <th>Monthly Reports Outstanding</th>
          </tr>
        </thead>
        <tbody>{data.map(MentorRow)}</tbody>
      </table>
    </div>
  );
}

export default Mentors;