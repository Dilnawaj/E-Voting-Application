

import { CandidateContext } from "../context/CandidateContext";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { VoterContext } from "../context/VoterContext";
import { AdminContext } from "../context/AdminContext";
import { doLogout, getCurrentUserDetail, getToken, isLoggedIn, isTokenExpired } from "../auth/Auth";
const Header = () => {
  
    const { admin, setAdmin } = useContext(AdminContext);
  const { setCandidate ,candidate} = useContext(CandidateContext);
    const { voter, setVoter } = useContext(VoterContext);
  const navigate = useNavigate();
   const [user, setUser] = useState(undefined);
   
 const [login, setLogin] = useState(false);
  const handleLogout = () => {
    
    logout({ setCandidate,candidate, voter, setVoter,admin,setAdmin, navigate });
  };

  useEffect(() => {
    
    const userDetail = getCurrentUserDetail();

    if (typeof userDetail !== "undefined") {
      const checkTokenExpiration = () => {
        
        const accessToken = getToken();

        if (isTokenExpired(accessToken)) {
          console.log("Access token expired");

          logout(true); // Call the logout function
        }
      };

      const intervalId = setInterval(checkTokenExpiration, 5000);

      // Cleanup interval on component unmount
      return () => clearInterval(intervalId);
    }
  }, [login]);
  useEffect(() => {
    if (typeof getCurrentUserDetail() !== "undefined") {
      
      setLogin(isLoggedIn());
      
      setUser(getCurrentUserDetail());
    }
  }, [login]);

  const logout = (sessionExpire) => {
    console.log("Seeesiosn", sessionExpire);

    setTimeout(() => {
      if (sessionExpire == true) {
        alert(
          "Session expired, Please do login again to continue using BloggerHub.",
          {
            style: {
              width: "580px",
            },
            autoClose: 12000, // Display the toast for 8 seconds
          }
        );
      }
      
      doLogout(() => {
        console.log("LOGOUT Boom");
        setLogin(false);
        navigate("/login");
      });
    }); // Adjust delay time as needed (in milliseconds)
  };
  const logoutAdmin = (sessionExpire) => {
    console.log("Seeesiosn", sessionExpire);

    setTimeout(() => {
      if (sessionExpire == true) {
        alert(
          "Session expired, Please do login again to continue using BloggerHub.",
          {
            style: {
              width: "580px",
            },
            autoClose: 12000, // Display the toast for 8 seconds
          }
        );
      }
      doLogout(() => {
        console.log("LOGOUT Boom");
        setLogin(false);
        navigate("/login/admin");
      });
    }); // Adjust delay time as needed (in milliseconds)
  };
 
  return (
    <header style={styles.header}>
      <div style={styles.leftSection}>
        <h2 style={styles.logo}>E-Voting System</h2>
      </div>
      <div style={styles.rightSection}>
   <span style={styles.username}>
  Welcome, {candidate && candidate.name
    ? candidate.name
    : voter && voter.name
    ? voter.name
    :admin && admin.name
    ? admin.name
    : 'User'}
</span>



        <button style={styles.logoutButton} onClick={logout}>Logout</button>
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
