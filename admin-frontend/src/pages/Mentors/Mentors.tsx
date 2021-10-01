import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';
import './styles/Mentors.css'

// import Tabs from './components/Tabs/Tabs';
// import Tab from './components/Tabs/Tab';

function Mentors() {
  return (
    <div className='mentors'>
      <h1>Mentors</h1>
    
      <Tabs defaultActiveKey="sessions" className = 'tab'>
      <Tab eventKey="sessions" title="Sessions">
        <p>
          Testing 123
        </p>
      </Tab>
      <Tab eventKey="questionnaire" title="Questionnaire">
        <ul>
          <li>a</li>
          <li>b</li>
        </ul>
      </Tab>
      <Tab eventKey="info" title="Personal Information">
        <p>
          Testing 789
        </p>
      </Tab>
      </Tabs>

      {/* <Tabs>
        <Tab title = "Sessions">

        </Tab>
        <Tab title = "Questionnaires">

        </Tab>
        <Tab title = "Personal Information">

        </Tab>
      </Tabs> */}
    </div>
  );
}

export default Mentors;