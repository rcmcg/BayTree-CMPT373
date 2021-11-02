import React from "react";
import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home from "./pages/Home/Home";
import Mentors from "./pages/Mentors/Mentors";
import Mentor from "./pages/Mentors/Mentor";
import Mentees from "./pages/Mentees/Mentees";
import Settings from "./pages/Settings/Settings";
import AddMentor from "./pages/AddMentor/AddMentor";
import Login from "./pages/Login/Login";
import Questionnaires from "./pages/Questionnaires/Questionnaires";

function App() {
  return (
    <div>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/mentors" component={Mentors} />
          <Route path="/mentor" component={Mentor} />
          <Route path="/mentees" component={Mentees} />
          <Route path="/add" component={AddMentor} />
          <Route path="/questionnaires" component={Questionnaires} />
          <Route path="/settings" component={Settings} />
          <Route path="/login" component={Login} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
