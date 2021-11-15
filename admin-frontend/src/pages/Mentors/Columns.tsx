import { ColumnFilter } from "./ColumnFilter";
import { format } from "date-fns";

// export const COLUMNS = [
//   {
//     Header: "Username",
//     accessor: "username" as const,
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Full Name",
//     accessor: "name" as const,
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Age",
//     accessor: "age" as const,
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Program",
//     accessor: "program" as const,
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Start Date",
//     accessor: "startDate" as const,
//     Cell: ({ value }: { value: Date }) => {
//       return format(new Date(value), "dd/MM/yyyy");
//     },
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Daily Sessions Outstanding",
//     accessor: "dailySessionsOutstanding" as const,
//     Filter: ColumnFilter,
//   },
//   {
//     Header: "Monthly Reports Outstanding",
//     accessor: "monthlyReportsOutstanding" as const,
//     Filter: ColumnFilter,
//   },
// ];

export const COLUMNS = [
  {
    Header: "ID",
    accessor: "viewsId" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "First Name",
    accessor: "firstName" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Last Name",
    accessor: "lastName" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Email",
    accessor: "email" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Volunteer Status",
    accessor: "status" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Start Date",
    accessor: "startDate" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "End Date",
    accessor: "endDate" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Phone Number",
    accessor: "phoneNumber" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Ethnicity",
    accessor: "ethnicity" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Address",
    accessor: "address" as const,
    Filter: ColumnFilter,
  },
  {
    Header: "Role",
    accessor: "role" as const,
    Filter: ColumnFilter,
  },
];