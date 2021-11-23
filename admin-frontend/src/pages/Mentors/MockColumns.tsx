import { ColumnFilter } from "./ColumnFilter";
import { format } from "date-fns";

export const MOCKCOLUMNS = [
  {
    Header: "Username",
    accessor: "username" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Name",
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
    Cell: ({ value }: { value: Date }) => {
      return format(new Date(value), "dd/MM/yyyy");
    },
    Filter: ColumnFilter,
  },
  {
    Header: "Daily Outstanding",
    accessor: "dailySessionsOutstanding" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Monthly Outstanding",
    accessor: "monthlyReportsOutstanding" as const,
    Filter: ColumnFilter,
  },
];
