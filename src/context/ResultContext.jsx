import React, { createContext,  useState } from "react";

export const ResultContext = createContext();

export const ResultProvider = ({ children }) => {
  const [result, setResult] = useState([]);
  const [loading, setLoading] = useState(false);

  return (
    <ResultContext.Provider value={{ result, setResult, loading, setLoading }}>
      {children}
    </ResultContext.Provider>
  );
};
