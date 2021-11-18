import {FaTimes} from 'react-icons/fa'

interface Task {
    id: number,
    text: string,
    day: string,
    reminder: boolean
    onDelete: any
}

const Task = (props:Task) => {
    return (
        <div className='task'>
            <h3>
                {props.text}
                <FaTimes
                    style={{ color: 'red', cursor: 'pointer'}}
                    onClick={() => props.onDelete(props.id)}
                />
            </h3>
            <p>{props.day}</p>
        </div>
    );
};

export default Task;