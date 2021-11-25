import React from 'react';
import Button from "./Button";
import "../../css/goal/goal.css"

interface Title{
    title: string
    onAdd(b:boolean): any,
    showAdd: boolean
}

const Header = (props:Title) => {
    return (
        <header className='header'>
            <h2>{props.title}</h2>
            <Button
                color={props.showAdd ? 'red' : 'green'}
                text={props.showAdd ? 'Close' : 'Add'}
                onClick={props.onAdd}
            />
        </header>
    );
};

Header.defaultProps = {
    title: 'Task Tracker'
}

export default Header;