import React from 'react';
import { useState } from "react";
import Header from '../components/goal/Header'
import Tasks from '../components/goal/Tasks'

export const Goals = () => {
    const [tasks, setTasks] = useState([
        {
            id: 1,
            text: 'Doctors Appointment',
            day: 'Feb 5th at 2:30pm',
            reminder: true,
        },
        {
            id: 2,
            text: 'Meeting at School',
            day: 'Feb 6th at 1:30pm',
            reminder: true,
        },
        {
            id: 3,
            text: 'Food Shopping',
            day: 'Feb 7th at 2:30pm',
            reminder: false,
        },
    ])

    // Delete task
    const deleteTask = (id:number) => {
        setTasks(tasks.filter((task) => task.id !== id))
    }

    return (
        <div className="containerGoal">
            <Header/>
            {tasks.length > 0 ? <Tasks
                tasks={tasks}
                onDelete={deleteTask}
            /> : 'No Tasks to Show'}
        </div>
    );
};

// export default Goals;