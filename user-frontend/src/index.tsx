import React from 'react';
import ReactDOM from 'react-dom';
import './css/dashboard/App.css';
import App from './App';
import Api from './pages/api';


const runApp = async () => {
  Api.init();

ReactDOM.render(
    <App />,
  document.getElementById('root')
);
}

runApp()
