import React from 'react';

interface ButtonInterface {
    color?: string,
    text?: string,
    onClick?: any
}

const Button = (props:ButtonInterface) => {
    return (
        <button
            onClick={props.onClick}
            style={{backgroundColor: props.color}}
            className='btn'>
            {props.text}
        </button>
    );
};

Button.defaultProps = {
    color: 'steelblue',
    text: 'Add'
}

export default Button;