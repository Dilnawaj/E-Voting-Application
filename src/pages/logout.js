
export const logout = ({ setCandidate,  navigate }) => {
  // Clear Recoil state
  
  // useSetRecoilState(emailAtom)("");
 
  
  // Clear context data
  //setCandidate(null);

  // Optionally clear localStorage/sessionStorage
  localStorage.clear();

  // Redirect to login
  navigate("/login");
};
