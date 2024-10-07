import React, { createContext, useState } from 'react';

// User Context
const UnpaidTax = createContext();

export const UnpaidTaxProvider = ({ children }) => {
  const [unpaidTax, setUnpaidTax] = useState([]);

  return (
    <UnpaidTax.Provider value={{ unpaidTax,setUnpaidTax}}>
      {children}
    </UnpaidTax.Provider>
  );
}

export default UnpaidTax;
