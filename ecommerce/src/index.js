import React from 'react';
// import ReactDOM from 'react-dom/client';
import{createRoot} from "react-dom/client";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { KeycloakProvider } from "keycloak-react-web";
import UserService from './service/UserService';



const renderApp=createRoot(document.getElementById("root")).render(
     
           <App />
   
  );
  // UserService.initKeycloak(renderApp);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
