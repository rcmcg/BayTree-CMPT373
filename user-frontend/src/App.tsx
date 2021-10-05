import React from 'react';
import './css/App.css';
import {NavigationBar} from "./components/NavigationBar";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import {Dashboard} from "./pages/dashboard";
import {Profile} from "./pages/profile";
import {Resources} from "./pages/resources";
import {SingleQuestionnaire} from "./pages/SingleQuestionnaire";
import {SingleNotification} from "./pages/SingleNotification";
import {FullHistoricalRecord} from "./pages/FullHistoricalRecord"

function App() {
    return(
        <Router>

            <NavigationBar/>
            <Switch>
                <Route path={"/"} exact component={Dashboard} />
                <Route path={`SingleNotification/:notificationId`} component={SingleNotification}/>
                <Route path={`/SingleQuestionnaire/:month`} component={SingleQuestionnaire} />

                <Route path={`/HistoricalRecord`} component={FullHistoricalRecord} />
                <Route path={"/profile"} exact component={Profile}/>
                <Route path={"/resources"} exact component={Resources}/>
            </Switch>

        </Router>
    )
}

export default App;
