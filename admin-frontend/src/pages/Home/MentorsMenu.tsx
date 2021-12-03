import React, {useContext, useEffect, useState } from "react";
import axios, {AxiosError, AxiosResponse} from "axios";
import Paper from '@material-ui/core/Paper';
import {
    ArgumentAxis, Title,
    ValueAxis,
    BarSeries,
    Chart,
} from '@devexpress/dx-react-chart-material-ui';
import { ThemeProvider, createMuiTheme } from '@material-ui/core/styles';

import { ValueScale, Animation } from '@devexpress/dx-react-chart';

const theme = createMuiTheme({
    palette: {
        type: "dark",
    }
});

interface Props {
    mentors: any
}

interface IDataItem {
    numSessionType: string,
    numberOfSessions: number,
}

export const MentorsMenu:  React.FC<Props> = ({mentors})=> {

    let [attendedSessions, setAttendedSessions] = useState<number>(0);

    const handleSelectMentor = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.value;
        fetchSessions(value);
    }
    const fetchSessions  = async (id: String) => {
        axios.get('http://localhost:8080/sessions/get/views/'+ id)
            .then((res: any) => {
                if(res.date !== null) {
                    // console.log(res.data)
                    // console.log("numSessions: " + res.data.length);
                    setAttendedSessions(res.data.length);
                    // console.log("Attended Sessions: " +attendedSessions);

                }
            })
            .catch((err: any) => {
                console.log(err);
            })
    }

    const getRemainingSessions =() => {
        // mentoring session held once per week
        let currentWeekNumber = require('current-week-number');
        let current = currentWeekNumber();
        const TOTAL_WEEKS_OF_A_YEAR = 52;
        let upcomingSessions = TOTAL_WEEKS_OF_A_YEAR - current;
        // console.log("current " +current);
        return upcomingSessions;
    }
    let upcomingSessions = getRemainingSessions();

    const chartData: IDataItem[] = [
        {numSessionType: "Attended Sessions", numberOfSessions: attendedSessions!},
        {numSessionType: "Upcoming Sessions", numberOfSessions: upcomingSessions},
        {numSessionType: "Total Sessions", numberOfSessions: attendedSessions!+upcomingSessions},
    ]

    // console.log(mentors);
    return (
        <div className={"menu"}>
            <div>

                <div className={"box"}>
                    <div className={"select-box"}>
                        <label className={"top-child"} form="selectMentorId"> Mentor Name  </label>
                        <select className={"top-child"} name={"mentorId"}
                                onChange={handleSelectMentor}>
                            <option> Select a mentor</option>
                            {mentors.map((mentor: { [x: string]: string; }) =>
                                <option key={mentor["viewsId"]} value = {mentor["viewsId"]} >
                                    {mentor["firstName"] + " " + mentor["lastName"]}
                                </option>)}
                        </select>
                    </div>
                </div>

                <div className={"box"}>
                    <div className={"attend-box"}>
                        <p>Attended sessions</p>
                        <p>{attendedSessions}</p>
                    </div>
                </div>
                <div className={"box"}>
                    <div className={"upcoming-box"}>
                        <p>Upcoming sessions</p>
                        <p>{upcomingSessions}</p>
                    </div>
                </div>
            </div>

            <ThemeProvider theme={theme}>
                <div className={"chart"}>
                    <Paper>
                        <Chart data = {chartData}
                            width={600} height={300}>
                            <ValueScale name={"numberOfSessions"}/>
                            <ArgumentAxis />
                            <ValueAxis scaleName="numberOfSessions" showGrid={false} showLine={true} showTicks={true} />

                            <BarSeries
                                name="Number of Sessions Bar"
                                valueField="numberOfSessions"
                                argumentField="numSessionType"
                                scaleName="numberOfSessions"

                            />
                            <Animation/>
                            <Title text="Number of sessions" />
                        </Chart>
                    </Paper>
                </div>
            </ThemeProvider>

        </div>

    );
}


