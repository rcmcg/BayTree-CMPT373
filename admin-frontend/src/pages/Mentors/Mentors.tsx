import React, { useState } from "react";
import "./Styles/Mentors.css";
import data from "./mockdata.json";

interface IState {
  mentors: {
    Username: string;
    Name: string;
    Age: number;
    Program: string;
    StartDate: Date;
    DailySessionsOutstanding: number;
    MonthlyReportsOutstanding: number;
  }[];
}

function Mentors() {
  const [mentors, setMentors] = useState<IState["mentors"]>(data);

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
        <tbody>
          {mentors.map((mentor) => (
            <tr>
              <td>{mentor.Username}</td>
              <td>{mentor.Name}</td>
              <td>{mentor.Age}</td>
              <td>{mentor.Program}</td>
              <td>{mentor.StartDate}</td>
              <td>{mentor.DailySessionsOutstanding}</td>
              <td>{mentor.MonthlyReportsOutstanding}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Mentors;
