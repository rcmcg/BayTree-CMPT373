import React from 'react';
import Button from "./Button";
import "../../css/goal/goal.css"

interface Title{
    title?: string
}

const Header = (props:Title) => {
    const onClick = () => {
        console.log('click')
    }

    return (
        <header className='header'>
            <h2>{props.title}</h2>
            <Button onClick={onClick}/>
        </header>
    );
};

Header.defaultProps = {
    title: 'Task Tracker'
}

export default Header;