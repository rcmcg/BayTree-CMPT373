import React from 'react';
import { useState } from "react";
import Header from '../components/goal/Header'
import Tasks from '../components/goal/Tasks'
import AddTask from '../components/goal/AddTask'

export const Goals = () => {
    const [showAddTask, setShowAddTask] = useState(false)
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

    // Add task
    const addTask = (task:any) => {
        const id = Math.floor(Math.random() * 10000) + 1

        const newTask = { id, ...task }
        setTasks([...tasks, newTask])
    }

    // Delete task
    const deleteTask = (id:number) => {
        setTasks(tasks.filter((task) => task.id !== id))
    }

    // Toggle Reminder
    const toggleReminder = (id:number) => {
        setTasks(
            tasks.map((task) =>
                task.id === id
                    ? { ...task, reminder: !task.reminder }
                    : task
            )
        )
    }

    return (
        <div className="containerGoal">
            <Header
                onAdd={() => setShowAddTask(!showAddTask)}
                showAdd={showAddTask}
            />
            {showAddTask && <AddTask onAdd={addTask}/>}
            {tasks.length > 0 ? <Tasks
                tasks={tasks}
                onDelete={deleteTask}
                onToggle={toggleReminder}
            /> : 'No Tasks to Show'}
        </div>
    );
};

// export default Goals;