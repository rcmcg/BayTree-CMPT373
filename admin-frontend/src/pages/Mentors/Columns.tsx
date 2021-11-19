import { ColumnFilter } from "./ColumnFilter";

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