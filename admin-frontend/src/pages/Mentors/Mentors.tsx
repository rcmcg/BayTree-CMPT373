// import React, { useState } from "react";
// import "./Styles/Mentors.css";
// import data from "./mockdata.json";
// import MentorRow from "./MentorRow";

// // May be necessary later, do not remove yet
// // interface MentorStateInterface {
// //   mentor: {
// //     id: number;
// //     username: string;
// //     name: string;
// //     age: number;
// //     program: string;
// //     startDate: string;
// //     dailySessionsOutstanding: number;
// //     monthlyReportsOutstanding: number;
// //   }[];
// // }

// function Mentors() {
//   // const [mentors, setMentors] = useState<MentorStateInterface["mentor"]>(data);

//   return (
//     <div className="mentors">
//       <h1>Mentors</h1>
//       <table>
//         <thead>
//           <tr>
//             <th>Username</th>
//             <th>Name</th>
//             <th>Age</th>
//             <th>Program</th>
//             <th>Start Date</th>
//             <th>Daily Sessions Outstanding</th>
//             <th>Monthly Reports Outstanding</th>
//           </tr>
//         </thead>
//         <tbody>{data.map(MentorRow)}</tbody>
//       </table>
//     </div>
//   );
// }

// export default Mentors;

import React, { useMemo, FC } from "react";
import { useTable, useFilters } from "react-table";
import DATA from "./mockdata.json";
import { COLUMNS } from "./Columns";

const Mentors = () => {
  const columns = useMemo(() => COLUMNS, []);
  const data = useMemo(() => DATA, []);

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable(
      {
        columns,
        data,
      },
      useFilters
    );

  return (
    <>
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps()}>
                  {column.render("Header")}
                  <div>{column.canFilter ? column.render("Filter") : null}</div>
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map((row) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map((cell) => {
                  return (
                    <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                  );
                })}
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
};

export default Mentors;
