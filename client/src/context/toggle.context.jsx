import { createContext, useState } from 'react'

export const ToggleContext = createContext({
  toggleSignIn: false,
  setIsLogin: () => {}
})

export const ToggleProvider = ({ children }) => {
  const [toggleSignIn, setToggleSignIn] = useState(false)

  const value = { toggleSignIn, setToggleSignIn }

  return (
    <ToggleContext.Provider value={value}>{children}</ToggleContext.Provider>
  )
}
