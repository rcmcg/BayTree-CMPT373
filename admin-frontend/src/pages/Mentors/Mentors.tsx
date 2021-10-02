import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import './styles/Mentors.css'
import {Link} from 'react-router-dom';

import { mentorData } from '../../assets/dummy-data/DummyMentors'; 

function Mentors() {
  return (
    <div className='mentors'>
      <h1>Mentors</h1>
      <Link to= '/mentor'>
        <p>test</p>
      </Link>
    </div>
  );
}

export default Mentors;