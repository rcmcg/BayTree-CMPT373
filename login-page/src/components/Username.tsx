import React from 'react';
import "../css/username.css"
import "../css/styleguide.css"

function Username() {
    return (
        <input
            className="username helvetica-extra-light-mountain-mist-25px"
            name="userNameBox"
            placeholder="Username"
            type="text"
        />
    );
}

export default Username