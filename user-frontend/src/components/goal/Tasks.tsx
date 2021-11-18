import Task from './Task'

interface Tasks {
    tasks?: any,
    onDelete?: any
}

const Tasks = ({tasks, onDelete}:Tasks) => {
    return (
        <>
            {tasks.map((task:any) => {
                return <Task
                    key={task.id}
                    id={task.id}
                    text={task.text}
                    day={task.day}
                    reminder={task.reminder}
                    onDelete={onDelete}
                />
            })}
        </>
    );
};

export default Tasks;