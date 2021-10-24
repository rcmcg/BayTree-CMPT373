import { ColumnFilter } from "./ColumnFilter";

export const COLUMNS = [
  {
    Header: "Username",
    accessor: "username",
    Filter: ColumnFilter,
  },
  {
    Header: "Full Name",
    accessor: "name",
    Filter: ColumnFilter,
  },
  {
    Header: "Age",
    accessor: "age",
    Filter: ColumnFilter,
  },
  {
    Header: "Program",
    accessor: "program",
    Filter: ColumnFilter,
  },
  {
    Header: "Start Date",
    accessor: "startDate",
    Filter: ColumnFilter,
  },
  {
    Header: "Daily Sessions Outstanding",
    accessor: "dailySessionsOutstanding",
    Filter: ColumnFilter,
  },
  {
    Header: "Monthly Reports Outstanding",
    accessor: "monthlyReportsOutstanding",
    Filter: ColumnFilter,
  },
];
