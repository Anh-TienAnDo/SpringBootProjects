import React, { createContext, useState } from 'react';

// User Context
const TaxWantPay = createContext();

export const TaxWantPayProvider = ({ children }) => {
  const [taxWantPay, setTaxWantPay] = useState([]);

  return (
    <TaxWantPay.Provider value={{ taxWantPay,setTaxWantPay}}>
      {children}
    </TaxWantPay.Provider>
  );
}

export default TaxWantPay;
