import * as React from 'react';
import Username from "../components/Username";
import Password from "../components/Password";

export const LoginPage = () => {

    return (
        <div className="container-center-horizontal">
            <div className="loginpage screen">
                <div className="overlap-group1" style={{ backgroundImage: `url(${overlapGroup1})` }}>
                    <img className="login-window" src={"../image/login-page-border.png"} />
                    <img className="baytree-logo" src={"../image/baytree-logo.png"} />
                    <div className="login-button">
                        <div className="overlap-login-button">
                            <img className="login-button-border" src={"../image/login-button-boundary.png"} />
                            <img className="login-text" src={"../image/login-button-text.png"} />
                        </div>
                    </div>
                    <img className="i-forgot-my-password-redirection" src={"../image/i-forgot-my-password.png"} />
                    <img className="mentorship-programme-text" src={"../image/mentorship-programme-text.png"} />
                    <img className="what-type-of-user-text" src={"../image/what-type-of-user-text.png"} />
                    <img className="account-type-selected" src={"../image/user-type-selected.png"} />
                    <img className="account-type-unselected" src={"../image/user-type-not-selected.png"} />
                    <img className="admin-text" src={"../image/admin-text.png"} />
                    <img className="mentor-mentee-text" src={"../image/mentor-mentee-text.png"} />
                    <img className="login-page-text" src={"../image/login-page-text.png"} />
                    < Username />
                    < Password />
                </div>
            </div>
        </div>
    );
}