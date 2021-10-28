import "./css/App.css";
import React from "react";
import { Switch, BrowserRouter as Router, Route } from "react-router-dom";
import {LoginPage} from "../src/pages/login-page"

export const backendApiURL: string = "http://localhost:8080"
export const HTTP_CREATED_STATUS_RESPONSE: number = 201

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={LoginPage} />
            </Switch>
        </Router>
    );
}

export default App;