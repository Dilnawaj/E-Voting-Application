

import { CandidateContext } from "../context/CandidateContext";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { logout } from "../pages/logout";
const Header = () => {
    
  const { setCandidate ,candidate} = useContext(CandidateContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    
    logout({ setCandidate,candidate, navigate });
  };
  return (
    <header style={styles.header}>
      <div style={styles.leftSection}>
        <h2 style={styles.logo}>E-Voting System</h2>
      </div>
      <div style={styles.rightSection}>
      <span style={styles.username}>
  Welcome, {candidate && candidate.name ? candidate.name : 'User'}
</span>

        <button style={styles.logoutButton} onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};

const styles = {
  header: {
    background: "linear-gradient(to right, #0f2027, #203a43, #2c5364)",
    color: "#fff",
    padding: "15px 30px",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.3)",
    position: "sticky",
    top: 0,
    zIndex: 1000,
  },
  leftSection: {
    display: "flex",
    alignItems: "center",
  },
  logo: {
    fontSize: "22px",
    fontWeight: "bold",
    margin: 0,
  },
  rightSection: {
    display: "flex",
    alignItems: "center",
    gap: "15px",
  },
  username: {
    fontSize: "16px",
    fontWeight: "500",
  },
  logoutButton: {
    padding: "8px 16px",
    backgroundColor: "#ff4d4d",
    border: "none",
    color: "#fff",
    borderRadius: "8px",
    cursor: "pointer",
    transition: "background-color 0.3s",
  },
};

export default Header;
