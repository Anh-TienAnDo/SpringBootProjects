import React, { createContext, useState } from 'react';

// User Context
const Search = createContext();

export const SearchProvider = ({ children }) => {
  const [search, setSearch] = useState({});

  return (
    <Search.Provider value={{ search,setSearch}}>
      {children}
    </Search.Provider>
  );
}

export default Search;
