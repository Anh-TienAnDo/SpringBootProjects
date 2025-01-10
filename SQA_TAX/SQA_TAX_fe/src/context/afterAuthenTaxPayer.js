import React, { createContext, useState } from 'react';

// User Context
const AuthenTaxPayer = createContext();

export const AuthenTaxPayerProvider = ({ children }) => {
  const [afterAuthenTaxPayer, setAfterAuthenTaxPayer] = useState(false);

  return (
    <AuthenTaxPayer.Provider value={{ afterAuthenTaxPayer,setAfterAuthenTaxPayer}}>
      {children}
    </AuthenTaxPayer.Provider>
  );
}

export default AuthenTaxPayer;
