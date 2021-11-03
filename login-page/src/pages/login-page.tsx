
import * as React from 'react';
import Username from "../components/Username";
import Password from "../components/Password";
import "../css/LoginPage.css"

export const LoginPage = () => {

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
                        <div className="overlap-login-button">
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
                    <img className="mentor-mentee-text" src={"../Images/mentor-mentee-text.png"} />
                    <img className="login-page-text" src={"../Images/login-page-text.png"} />
                    < Username />
                    < Password />
                </div>
            </div>
        </div>
    );
}