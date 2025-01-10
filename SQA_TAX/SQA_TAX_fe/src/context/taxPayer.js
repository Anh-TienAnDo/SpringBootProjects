import React, { createContext, useState } from 'react';

// User Context
const TaxPayer = createContext();

export const TaxPayerProvider = ({ children }) => {
  const [taxPayer, setTaxPayer] = useState({});

  return (
    <TaxPayer.Provider value={{ taxPayer,setTaxPayer}}>
      {children}
    </TaxPayer.Provider>
  );
}

export default TaxPayer;
