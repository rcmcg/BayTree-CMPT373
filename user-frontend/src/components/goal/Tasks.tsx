import Task from './Task'

interface Tasks {
    tasks: any,
    onDelete: any,
    // onToggle: any
}

const Tasks = ({tasks, onDelete, /*onToggle*/}:Tasks) => {
    return (
        <>
            {tasks.map((task:any) => {
                return <Task
                    key={task.id}
                    id={task.id}
                    text={task.text}
                    // day={task.day}
                    // reminder={task.reminder}
                    onDelete={onDelete}
                    // onToggle={onToggle}
                />
            })}
        </>
    );
};

export default Tasks;