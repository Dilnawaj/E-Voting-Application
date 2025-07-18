import React, { createContext, useState } from "react";

export const VoterContext = createContext();

export const VoterProvider = ({ children }) => {
  const [voter, setVoter] = useState([]);
  const [loading, setLoading] = useState(false);
  const addVoter = (newVoter) => setVoter(newVoter)
  return (
    <VoterContext.Provider value={{ voter, setVoter,addVoter, loading, setLoading }}>
      {children}
    </VoterContext.Provider>
  );
};
