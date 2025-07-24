import "./App.css";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import CandidateRegistrationPage from "./pages/candidate/CandidateRegistrationPage";
import VoterRegistrationPage from "./pages/voter/VoterRegistrationPage";
import VotingPage from "./pages/voter/VotingPage";
import LoginPage from "./pages/LoginPage";
import VoterDashboard from "./pages/voter/VoterDashboard";
import CandidateDashboard from "./pages/candidate/CandidateDashboard";
import CandidateProfile from "./pages/candidate/CandidateProfile";
import CandidateVotes from "./pages/candidate/CandidateVotes";
import CandidateElection from "./pages/candidate/CandidateElection";
import VoterInsightsPage from "./pages/candidate/VoterInsightsPage";
import NotificationList from "./pages/candidate/NotificationList";
import AdminRegistration from "./pages/election-commission/AdminRegistration";
import AdminDashboard from "./pages/election-commission/AdminDashboard";
function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route
            path="/candidate/register-candidate"
            element={<CandidateRegistrationPage />}
          />
          
          <Route
            path="/admin/register-admin"
            element={<AdminRegistration />}
          />
          
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          <Route path="/voter/register-voter" element={<VoterRegistrationPage />} />
          <Route path="/" element={<LoginPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/vote" element={<VotingPage />} />
          <Route path="/voter/dashboard" element={<VoterDashboard />} />
            <Route path="/voter/profile" element={<VoterDashboard />} />
              <Route path="/voter/election" element={<VoterDashboard />} />
          <Route path="/candidate/dashboard" element={<CandidateDashboard />} />
          <Route path="/candidate/profile" element={<CandidateProfile />} />
          <Route path="/candidate/election" element={<CandidateElection />} />
          <Route path="/candidate/votes" element={<CandidateVotes />} />
          <Route path="/candidate/notification" element={<NotificationList />} />
          
          <Route path="/candidate/voter-insights" element={<VoterInsightsPage />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
