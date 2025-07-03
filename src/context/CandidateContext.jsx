import React, {  createContext, useState } from "react";

export const CandidateContext = createContext();

export const CandidateProvider = ({ children }) => {
  const [candidates, setCandidates] = useState([]);
const addCandidate = (newCandidate) => setCandidates(prev => [...prev, newCandidate]);

  return (
    <CandidateContext.Provider value={{ candidates, setCandidates }}>
      {children}
    </CandidateContext.Provider>
  );
};
