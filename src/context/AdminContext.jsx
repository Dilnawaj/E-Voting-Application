import React, { createContext, useState } from "react";

export const AdminContext = createContext();


export const AdminProvider = ({ children }) => {
  const [admin, setAdmin] = useState(null);

  const addAdmin = (newAdmin) => setAdmin(newAdmin);

  return (
    <AdminContext.Provider value={{ admin, setAdmin, addAdmin }}>
      {children}
    </AdminContext.Provider>
  );
}