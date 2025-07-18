import React, { createContext, useState } from "react";

export const CandidateContext = createContext();

export const CandidateProvider = ({ children }) => {
  const [candidate, setCandidate] = useState(null);

  const addCandidate = (newCandidate) => setCandidate(newCandidate);

  return (
    <CandidateContext.Provider value={{ candidate, setCandidate, addCandidate }}>
      {children}
    </CandidateContext.Provider>
  );
};
