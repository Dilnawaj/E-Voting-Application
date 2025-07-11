import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { CandidateProvider } from './context/CandidateContext';
import 'bootstrap/dist/css/bootstrap.min.css';

import { RecoilRoot } from 'recoil';
import { VoterProvider } from './context/VoterContext';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <CandidateProvider>
      <VoterProvider>
         <RecoilRoot>
    <App />
    </RecoilRoot>
    </VoterProvider>
    </CandidateProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
