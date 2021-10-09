import React, {useState} from 'react';
import MyButton from "../../components/UI/button/MyButton";
import MyInput from "../../components/UI/input/MyInput";
import classes from './Login.module.css'

const Login = () => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');

    const logIn = (e:any) => {
        e.preventDefault();
        console.log(login)
        console.log(password)
    }

    return (
        <div >
            <form className={classes.login}>
                <MyInput
                    value={login}
                    onChange={(e:any) => setLogin(e.target.value)}
                    type="text"
                    placeholder="Enter your login"
                />
                <MyInput
                    value={password}
                    onChange={(e:any) => setPassword(e.target.value)}
                    type="password"
                    placeholder="Enter your password"
                />
                <MyButton onClick={logIn}>Login in</MyButton>
            </form>
        </div>
    );
};

export default Login;