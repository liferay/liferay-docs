import React from 'react';
import ReactDOM from 'react-dom';
//import './index.css';//removed for Portal Migration
import App from './App';
import * as serviceWorker from './serviceWorker';
import { HashRouter as Router } from 'react-router-dom';

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
//serviceWorker.unregister();

export default function main({portletNamespace, contextPath, portletElementId}) {
    
      ReactDOM.render((
        <div className="mx-auto">
          <div className="ml-3 mr-3 mt-3">
            <img src="/o/my-generated-react-guestbook-app/logo.svg" className="App-logo" alt="logo" />
            <Router>
              <App/>
            </Router>
          </div>
        </div>
      ), document.getElementById(portletElementId));
    
}

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();