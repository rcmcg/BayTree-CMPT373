import React from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages/Home/Home';
import Mentors from './pages/Mentors/Mentors';
import Mentor from './pages/Mentors/Mentor';
import Mentees from './pages/Mentees/Mentees';
import Settings from './pages/Settings/Settings'
import AddMentor from './pages/AddMentor/AddMentor';
import Login from "./pages/Login/Login";

export const backendApiURL: string = "http://localhost:8080"
export const HTTP_CREATED_STATUS_RESPONSE: number = 201

function App() {
  return (
    <div>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/mentors' component={Mentors} />
          <Route path='/mentor' component={Mentor} />
          <Route path='/mentees' component={Mentees} />
          <Route path='/add' component={AddMentor} />
          <Route path='/settings' component={Settings} />
          <Route path='/login' component={Login} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;