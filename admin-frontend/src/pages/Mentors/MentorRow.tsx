import React from "react";
import { useHistory } from "react-router";

export interface MentorObjectInterface {
  mentor: {
    id: number;
    username: string;
    name: string;
    age: number;
    program: string;
    startDate: string;
    dailySessionsOutstanding: number;
    monthlyReportsOutstanding: number;
  };
}

function MentorRow(singleMentorData: MentorObjectInterface["mentor"]) {
  const history = useHistory();

  const goToUser = (username: string) => {
    console.log(username);
    history.push("/Mentor");
  };

  return (
    <tr
      onClick={() => {
        goToUser(singleMentorData.username);
      }}
    >
      <td>{singleMentorData.username}</td>
      <td>{singleMentorData.name}</td>
      <td>{singleMentorData.age}</td>
      <td>{singleMentorData.program}</td>
      <td>{singleMentorData.startDate}</td>
      <td>{singleMentorData.dailySessionsOutstanding}</td>
      <td>{singleMentorData.monthlyReportsOutstanding}</td>
    </tr>
  );
}

export default MentorRow;
