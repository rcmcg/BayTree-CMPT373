import React from 'react';
import './css/App.css';
import {NavigationBar} from "./components/NavigationBar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Session} from "./pages/session";
import {Dashboard} from "./pages/dashboard";
import {Profile} from "./pages/profile";
import {Resources} from "./pages/resources";

export const backendApiURL: string = "http://localhost:8080"
export const HTTP_CREATED_STATUS_RESPONSE: number = 201

function App() {
    return(
        <Router>
            <NavigationBar/>
            <Switch>
                <Route path={"/"} exact component={Dashboard} />
                <Route path={"/session"} exact component={Session} />
                <Route path={"/profile"} exact component={Profile}/>
                <Route path={"/resources"} exact component={Resources}/>
            </Switch>
        </Router>
    )
}

export default App;