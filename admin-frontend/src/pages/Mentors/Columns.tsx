import { ColumnFilter } from "./ColumnFilter";

export const COLUMNS = [
  {
    Header: "Username",
    accessor: "username" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Full Name",
    accessor: "name" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Age",
    accessor: "age" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Program",
    accessor: "program" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Start Date",
    accessor: "startDate" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Daily Sessions Outstanding",
    accessor: "dailySessionsOutstanding" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Monthly Reports Outstanding",
    accessor: "monthlyReportsOutstanding" as const,
    Filter: ColumnFilter,
  },
];
