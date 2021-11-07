import React from 'react'
import "../css/LoginPage.css"
import "../css/password.css"
import "../css/username.css"
import "../css/styleguide.css"
import {AxiosError, AxiosResponse} from "axios";
import {backendApiURL, HTTP_CREATED_STATUS_RESPONSE} from "../App";

const axios = require('axios').default;

interface LoginState {
    username: string,
    password: string
}

export class LoginForm extends React.Component<{}, LoginState> {
    constructor(props: any) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.resetForm = this.resetForm.bind(this);
    }

    handleSubmit(event: any) {
        this.setState({
            username: this.state.username,
            password: this.state.password
        }, this.processLogin)
        this.resetForm(event)
        event.preventDefault()
    }

    processLogin() {
        // TODO: Send Post Request to backend to retrieve a security token
    }

    handleUsernameChange(event: any) {
        this.setState({username: event.target.value});
    }

    handlePasswordChange(event: any) {
        this.setState({password: event.target.value});
    }

    resetForm(event: any) {
        this.setState({
            username: "",
            password: ""
        })
    }

    render() {
        return (
            <div className="container-center-horizontal">
                <div className="loginpage">
                    <div className="baytree-leaf-background" style={{ backgroundImage: `url(${"../Images/baytree-leaves.png"})` }}>
                        <img className="login-window" src={"../Images/login-page-border.png"} />
                        <img className="baytree-logo" src={"../Images/baytree-logo.png"} />
                        <img className="username-textbox" src={"../Images/username-textbox.png"}/>
                        <img className="username-text" src={"../Images/username-text.png"}/>
                        <img className="password-text" src={"../Images/password-text.png"} />
                        <img className="password-textbox" src={"../Images/password-textbox.png"} />
                        <div className="login-button">
                            <div className="overlap-login-button" onClick={this.handleSubmit}>
                                <img className="login-button-border" src={"../Images/login-button-boundary.png"} />
                                <img className="login-text" src={"../Images/login-button-text.png"} />
                            </div>
                        </div>
                        <img className="i-forgot-my-password-redirection" src={"../Images/i-forgot-my-password.png"} />
                        <img className="mentorship-programme-text" src={"../Images/mentorship-programme-text.png"} />
                        <img className="what-type-of-user-text" src={"../Images/what-type-of-user-text.png"} />
                        <img className="account-type-selected" src={"../Images/user-type-selected.png"} />
                        <img className="account-type-unselected" src={"../Images/user-type-not-selected.png"} />
                        <img className="admin-text" src={"../Images/admin-text.png"} />
                        <img className="mentor-text" src={"../Images/mentor-text.png"} />
                        <img className="login-page-text" src={"../Images/login-page-text.png"} />
                        <input
                            className="username helvetica-extra-light-mountain-mist-25px"
                            name="userNameBox"
                            value={this.state.username}
                            onChange = {this.handleUsernameChange}
                            placeholder="Username"
                            type="text"
                        />
                        <input
                            className="password helvetica-extra-light-mountain-mist-25px"
                            name="passwordBox"
                            value={this.state.password}
                            onChange = {this.handlePasswordChange}
                            placeholder="Password"
                            type="password"
                        />
                    </div>
                </div>
            </div>

        );
    }
}

