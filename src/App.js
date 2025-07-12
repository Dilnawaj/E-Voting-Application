import "./App.css";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import CandidateRegistrationPage from "./pages/candidate/CandidateRegistrationPage";
import VoterRegistrationPage from "./pages/voter/VoterRegistrationPage";
import VotingPage from "./pages/voter/VotingPage";
import AdminRegistration from "./pages/admin/AdminRegistration";
import LoginPage from "./pages/LoginPage";
import VoterDashboard from "./pages/voter/VoterDashboard";
import CandidateDashboard from "./pages/candidate/CandidateDashboard";
import CandidateProfile from "./pages/candidate/CandidateProfile";
import CandidateNotification from "./pages/candidate/CandidateNotification";
import CandidateVotes from "./pages/candidate/CandidateVotes";
import CandidateElection from "./pages/candidate/CandidateElection";
function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route
            path="/candidate/register-candidate"
            element={<CandidateRegistrationPage />}
          />
          <Route path="/register-voter" element={<VoterRegistrationPage />} />
          <Route path="/register-admin" element={<AdminRegistration />} />
          <Route path="/" element={<LoginPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/vote" element={<VotingPage />} />
          <Route path="/voter/dashboard" element={<VoterDashboard />} />
          <Route path="/candidate/dashboard" element={<CandidateDashboard />} />
          <Route path="/candidate/profile" element={<CandidateProfile />} />
          <Route path="/candidate/election" element={<CandidateElection />} />
          <Route path="/candidate/votes" element={<CandidateVotes />} />
          <Route path="/candidate/notification" element={<CandidateNotification />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
