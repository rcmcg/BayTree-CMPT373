import React, { useState, useMemo, useEffect } from "react";
import {
  useTable,
  useFilters,
  useSortBy,
  usePagination,
  useRowSelect,
} from "react-table";
import { COLUMNS } from "./Columns";
import CustomDatePicker from "./customDatePicker";
import moment from "moment";
import axios from "axios";
import { backendApiURL } from "../../App";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import { MentorInterface } from "./MentorInterfaces";

const Mentors = () => {
  const [mentors, setMentors] = useState<MentorInterface[]>([]);

  const getMentors = async () => {
    let url = backendApiURL + "/user/get/mentors/all";
    const response = await axios.get<MentorInterface[]>(url);
    return response.data;
  };

  useEffect(() => {
    const fetchMentors = async () => {
      const mentorData = await getMentors();
      setMentors(mentorData);
    };
    fetchMentors();
  }, []);

  // Code below mostly taken and based on // https://www.youtube.com/playlist?list=PLC3y8-rFHvwgWTSrDiwmUsl4ZvipOw9Cz
  // However, there do not seem to be many ways to modify the code as this is just how the library works
  // Code applied from various parts of the tutorial
  // -----
  const columns = useMemo(() => COLUMNS, []);
  var data = useMemo(() => mentors, [mentors]);

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    page,
    nextPage,
    previousPage,
    canNextPage,
    canPreviousPage,
    pageOptions,
    gotoPage,
    pageCount,
    setPageSize,
    state,
    prepareRow,
  } = useTable(
    {
      columns,
      data,
    },
    useFilters,
    useSortBy,
    usePagination,
    useRowSelect
  );

  const { pageIndex, pageSize } = state;

  const [startDate, setStartDate] = useState(new Date("2010-01-01T00:00:00"));
  const [finishDate, setFinishDate] = useState(
    new Date(moment().format("YYYY-MM-DDTHH:mm:ss"))
  );

  return (
    <>
      <CustomDatePicker
        startDate={startDate}
        setStartDate={setStartDate}
        finishDate={finishDate}
        setFinishDate={setFinishDate}
      />
      {/* Code mostly taken from and based on //
      https://www.youtube.com/playlist?list=PLC3y8-rFHvwgWTSrDiwmUsl4ZvipOw9Cz
      ----- */}
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps()}>
                  <span {...column.getSortByToggleProps()}>
                    {column.render("Header")}
                    {column.isSorted
                      ? column.isSortedDesc
                        ? " 🔽"
                        : " 🔼"
                      : ""}
                  </span>
                  <div>{column.canFilter ? column.render("Filter") : null}</div>
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {page.map((row) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map((cell) => {
                  return (
                    <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                  );
                })}
                <Link
                  to={{
                    pathname: `/mentor/${row.original.viewsId}`,
                    state: row.original,
                  }}
                >
                  <td>
                    <Button>Details</Button>
                  </td>
                </Link>
              </tr>
            );
          })}
        </tbody>
      </table>
      <div>
        <select
          value={pageSize}
          onChange={(e) => setPageSize(Number(e.target.value))}
        >
          {[10, 20, 50].map((pageSize) => (
            <option key={pageSize} value={pageSize}>
              Show {pageSize}
            </option>
          ))}
        </select>
        <span>
          Page {pageIndex + 1} of {pageOptions.length}
        </span>
        <span>
          | Go to page:{" "}
          <input
            type="number"
            defaultValue={pageIndex + 1}
            onChange={(e) => {
              const pageNumber = e.target.value
                ? Number(e.target.value) - 1
                : 0;
              gotoPage(pageNumber);
            }}
            style={{ width: "50px" }}
          />
        </span>{" "}
        <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}>
          {"<<"}
        </button>{" "}
        <button onClick={() => previousPage()} disabled={!canPreviousPage}>
          Prev
        </button>{" "}
        <button onClick={() => nextPage()} disabled={!canNextPage}>
          Next
        </button>{" "}
        <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>
          {">>"}
        </button>{" "}
      </div>
    </>
  );
};

export default Mentors;
