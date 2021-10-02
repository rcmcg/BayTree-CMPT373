import React from 'react';
import './css/App.css';
import {NavigationBar} from "./components/NavigationBar";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import {Dashboard} from "./pages/dashboard";
import {Profile} from "./pages/profile";
import {Resources} from "./pages/resources";
import {SingleQuestionnaire} from "./pages/SingleQuestionnaire";

function App() {
    return(
        <Router>

            <NavigationBar/>
            <Switch>
                <Route path={"/"} exact component={Dashboard} />
                <Route path={`/SingleQuestionnaire/:month`} component={SingleQuestionnaire} />

                <Route path={"/profile"} exact component={Profile}/>
                <Route path={"/resources"} exact component={Resources}/>
            </Switch>

        </Router>
    )
}

export default App;
