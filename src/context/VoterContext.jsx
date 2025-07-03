import React, { createContext, useState } from "react";

export const VoterContext = createContext();

export const VoterProvider = ({ children }) => {
  const [voters, setVoters] = useState([]);
  const [loading, setLoading] = useState(false);
  return (
    <VoterContext.Provider value={{ voters, setVoters, loading, setLoading }}>
      {children}
    </VoterContext.Provider>
  );
};
