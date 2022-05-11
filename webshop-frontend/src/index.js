import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);

// package.json (pom.xml) - kõik dependencied
// "npm install"

// Routing (href ei ole kasutusel), <Link>
// npm install react-router-dom

// BrowserRouter võtab koodilõigud react-router-dom seest
