import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import Api from './pages/Resources/api';

const runApp = async () => {
  Api.init();

ReactDOM.render(
    <App />
  document.getElementById('root')
);
}

runApp()
