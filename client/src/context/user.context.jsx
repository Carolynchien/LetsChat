import { createContext, useState } from 'react'

export const UserContext = createContext({
  user: null,
  setUser: () => {},
  authenticated: false,
  toggleAuthenticated: () => {}
})

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null)
  const [authenticated, toggleAuthenticated] = useState(false)
  const value = { authenticated, toggleAuthenticated, user, setUser }

  return <UserContext.Provider value={value}>{children}</UserContext.Provider>
}
