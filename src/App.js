
import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CandidateRegistrationPage from './pages/CandidateRegistrationPage';
import VoterRegistrationPage from './pages/VoterRegistrationPage';
import VotingPage from './pages/VotingPage';
import AdminRegistration from './pages/AdminRegistration';
import LoginPage from './pages/LoginPage';
import VoterDashboard from './pages/VoterDashboard';
function App() {
  return (
   <>

<Router>
  <Routes>

    <Route path ="/register-candidate"   element={<CandidateRegistrationPage/>}  />
    <Route path="/register-voter"  element={<VoterRegistrationPage/>}      />
    <Route path="/register-admin" element ={<AdminRegistration/>} />
    <Route path="/" element={<LoginPage />} />
    <Route path="/login" element={<LoginPage />} />
<Route path="/vote" element={<VotingPage />} />
<Route path="/voter/dashboard" element={<VoterDashboard />} />
  </Routes>
  
</Router>

   </>
  );
}

export default App;
