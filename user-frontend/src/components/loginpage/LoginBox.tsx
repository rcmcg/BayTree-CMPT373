import * as React from 'react';
import "../../css/loginpage/LoginPage.css"
import "../../css/loginpage/password.css"
import "../../css/loginpage/username.css"
import "../../css/loginpage/styleguide.css"
import baytreeLeafBackground from "../../resources/Static/Images/baytree-leaves.png"
import loginWindowBorder from "../../resources/Static/Images/login-page-border.png"
import baytreeLogo from "../../resources/Static/Images/baytree-logo.png"
import usernameTextBox from "../../resources/Static/Images/username-textbox.png"
import passwordTextBox from "../../resources/Static/Images/password-textbox.png"
import usernameText from "../../resources/Static/Images/username-text.png"
import passwordText from "../../resources/Static/Images/password-text.png"
import loginButtonBorder from "../../resources/Static/Images/login-button-boundary.png"
import loginButtonText from "../../resources/Static/Images/login-button-text.png"
import iForgotMyPasswordRedirection from "../../resources/Static/Images/i-forgot-my-password.png"
import mentorshipProgrammeText from "../../resources/Static/Images/mentorship-programme-text.png"
import whatTypeOfUsersText from "../../resources/Static/Images/what-type-of-user-text.png"
import userTypeSelected from "../../resources/Static/Images/user-type-selected.png"
import userTypeUnselected from "../../resources/Static/Images/user-type-not-selected.png"
import adminText from "../../resources/Static/Images/admin-text.png"
import mentorText from "../../resources/Static/Images/mentor-text.png"
import loginPageText from "../../resources/Static/Images/login-page-text.png"
import {Link} from "react-router-dom"
import {Context} from '../../index'
import {useContext, useState} from "react";
import {observer} from "mobx-react-lite";


//if you want to use checkAuth functionality that I have call in App.tsx, than you need to wrap up the
// LoginForm with observer (just how I did in App.tsx)

export function LoginForm() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    //IMPORTANT: need to be changed to call format. Hooks are not allowed in classes.
    const {store} = useContext(Context);

    const handleSubmit = (e: React.MouseEvent<HTMLDivElement>) => {
        e.preventDefault();
        store.login(username,password);
        setUsername("");
        setPassword("");
    }

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
        console.log(e.target.value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
        console.log(e.target.value);
    }

    return (
        <div className="container-center-horizontal">
            <div className="loginpage">
                <div className="baytree-leaf-background" style={{backgroundImage: `url(${baytreeLeafBackground})`}}>
                    <img className="login-window" src={loginWindowBorder}/>
                    <img className="baytree-logo" src={baytreeLogo}/>
                    <img className="username-textbox" src={usernameTextBox}/>
                    <img className="username-text" src={usernameText}/>
                    <img className="password-text" src={passwordText}/>
                    <img className="password-textbox" src={passwordTextBox}/>
                    <div className="login-button" onClick={handleSubmit}>
                        <div className="overlap-login-button">
                            <img className="login-button-border" src={loginButtonBorder}/>
                            <img className="login-text" src={loginButtonText}/>
                        </div>
                    </div>
                    <img className="i-forgot-my-password-redirection" src={iForgotMyPasswordRedirection}/>
                    <img className="mentorship-programme-text" src={mentorshipProgrammeText}/>
                    <img className="what-type-of-user-text" src={whatTypeOfUsersText}/>
                    <img className="account-type-selected" src={userTypeSelected}/>
                    <img className="account-type-unselected" src={userTypeUnselected}/>
                    <img className="admin-text" src={adminText}/>
                    <img className="mentor-text" src={mentorText}/>
                    <img className="login-page-text" src={loginPageText}/>
                    <input
                        className="username helvetica-extra-light-mountain-mist"
                        name="userNameBox"
                        value={username}
                        onChange={handleUsernameChange}
                        placeholder="Username"
                        type="text"
                    />
                    <input
                        className="password helvetica-extra-light-mountain-mist"
                        name="passwordBox"
                        value={password}
                        onChange={handlePasswordChange}
                        placeholder="Password"
                        type="password"
                    />
                </div>
            </div>
        </div>
    );
}

export default observer(LoginForm);

