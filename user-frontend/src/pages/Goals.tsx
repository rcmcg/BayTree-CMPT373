import React, {useContext, useEffect} from 'react';
import { useState } from "react";
import Header from '../components/goal/Header'
import Tasks from '../components/goal/Tasks'
import AddTask from '../components/goal/AddTask'
import $api from "../http";
import {Context} from "../index";

interface Goal {
    id: number,
    username: string,
    text: string,
    state: string
}

export const Goals = () => {
    const [showAddTask, setShowAddTask] = useState(false)
    const [tasks, setTasks] = useState<Goal[]>([])
    // const {store} = useContext(Context)

    useEffect(()=> {
        $api.get('/api/goal/get/all').then((res) => {
            setTasks(res.data)
            console.log(res.data)
        })
    }, [])

    // useEffect(() => {
    //     $api.post('api/goal/add')
    // }, [])

    // Add task
    const addTask = (task:any) => {
        $api.post('api/goal/add', {}).then((res) => {

        })
        // const id = Math.floor(Math.random() * 10000) + 1
        //
        // const newTask = { id, ...task }
        // setTasks([...tasks, newTask])
    }

    // Delete task
    const deleteTask = (id:number) => {
        setTasks(tasks.filter((task) => task.id !== id))
    }

    // Toggle Reminder
    // const toggleReminder = (id:number) => {
    //     setTasks(
    //         tasks.map((task) =>
    //             task.id === id
    //                 ? { ...task, reminder: !task.reminder }
    //                 : task
    //         )
    //     )
    // }

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
                // onToggle={toggleReminder}
            /> : 'No Tasks to Show'}
        </div>
    );
};

// export default Goals;