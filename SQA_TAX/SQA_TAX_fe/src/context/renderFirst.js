import React, { createContext, useState } from 'react';

// User Context
const RenderFirst = createContext();

export const RenderFirstProvider = ({ children }) => {
  const [renderFirst, setRenderFirst] = useState(false);

  return (
    <RenderFirst.Provider value={{ renderFirst,setRenderFirst}}>
      {children}
    </RenderFirst.Provider>
  );
}

export default RenderFirst;
