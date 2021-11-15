import React, { useState, useMemo } from "react";
import {
  useTable,
  useFilters,
  useSortBy,
  usePagination,
  useRowSelect,
} from "react-table";
import DATA from "./mockdata.json";
import { COLUMNS } from "./Columns";
import CustomDatePicker from "./customDatePicker";
import moment from "moment";
import Checkbox from "./Checkbox";
import axios, {AxiosResponse} from "axios";
import { backendApiURL } from "../../App";

const Mentors = () => {
  // Code mostly taken and based on // https://www.youtube.com/playlist?list=PLC3y8-rFHvwgWTSrDiwmUsl4ZvipOw9Cz
  // However, there do not seem to be many ways to modify the code as this is just how the library works
  // Code applied from various parts of the tutorial
  // -----

  function getMentorData() {
    let url = backendApiURL + "/user/get/mentors/all";

    let mentorArray: any[] = []
    axios.get(url)
      .then((response) => {
          const mentorData: any = response.data;
          mentorData.map((mentor: any) => {
            mentorArray.push(mentor);
          });
          console.log(mentorArray);
      })
      .catch(function (error) {
          console.log(error)
      })

      return mentorArray;
  };

  const data = useMemo(() => getMentorData(), []);

  const columns = useMemo(() => COLUMNS, []);
  // const data = useMemo(() => DATA, []);

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
    selectedFlatRows,
  } = useTable(
    {
      columns,
      data,
    },
    useFilters,
    useSortBy,
    usePagination,
    useRowSelect,
    (hooks) => {
      hooks.visibleColumns.push((columns) => [
        {
          id: "selection",
          Header: ({ getToggleAllRowsSelectedProps }) => (
            <Checkbox name={""} {...getToggleAllRowsSelectedProps()} />
          ),
          Cell: ({ row }: { row: any }) => (
            <Checkbox {...row.getToggleRowSelectedProps()} />
          ),
        },
        ...columns,
      ]);
    }
  );

  const { pageIndex, pageSize } = state;
  // -----

  const [startDate, setStartDate] = useState(new Date("2010-01-01T00:00:00"));
  const [finishDate, setFinishDate] = useState(
    new Date(moment().format("YYYY-MM-DDTHH:mm:ss"))
  );

  const [message, setMessage] = useState("");
  const [messageError, setMessageError] = useState("");
  const [listError, setlistError] = useState("");

  const handleMessageChange = (event: React.ChangeEvent<any>) => {
    setMessage(event.target.value);
  };

  let isValid: boolean = true;

  // const validate = () => {
  //   if (selectedFlatRows.map((row) => row.original.username).length === 0) {
  //     setlistError("No users selected");
  //   }
  //   if (message === "") {
  //     setMessageError("Empty message body");
  //   }
  //   if (listError !== "" || messageError !== "") {
  //     return false;
  //   }
  //   return true;
  // };

  // const handleSubmit = (event: React.ChangeEvent<any>) => {
  //   event.preventDefault();
  //   isValid = validate();
  //   if (isValid) {
  //     axios
  //       .post("http://localhost:8080/notifications/send", {
  //         usernameList: selectedFlatRows.map((row) => row.original.username),
  //         message: message,
  //       })
  //       .then((response) => {
  //         console.log(response);
  //       })
  //       .catch((error) => {
  //         console.log(error);
  //       });
  //     isValid = true;
  //     // setlistError("");
  //     // setMessageError("");
  //   }
  // };

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
      {/* <pre>
        <code>
          {JSON.stringify(
            {
              "Selected users": selectedFlatRows.map(
                (row) => row.original.username
              ),
            },
            null,
            2
          )}
        </code>
      </pre> */}
      {/* ----- */}
      {/* <div style={{ color: "red" }}> {listError}</div>
      <form onSubmit={handleSubmit}>
        <div>
          <textarea
            value={message}
            id="notifBody"
            onChange={handleMessageChange}
            rows={7}
            cols={42}
          ></textarea>
        </div>
        <div style={{ color: "red" }}>{messageError}</div>
        <button type="submit">Submit</button>
      </form> */}
    </>
  );
};

export default Mentors;
