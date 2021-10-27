import React from 'react'
import "../css/password.css"
import "../css/styleguide.css"

function Password() {
    return (
        <img className="password-textbox" src={"../image/password-textbox.png"} />
        <img className="password-text" src={"../image/password-text.png"} />
        <input
            className="password helvetica-extra-light-mountain-mist-25px"
            name="passwordbox"
            placeholder="Password"
            type="password"
        />
    );
}

export default Password