import { createContext, useState } from 'react'

export const ChatPersonContext = createContext({
  chatPerson: null,
  setChatPerson: () => {}
})

export const ChatPersonProvider = ({ children }) => {
  const [chatPerson, setChatPerson] = useState(null)

  const value = { chatPerson, setChatPerson }

  return (
    <ChatPersonContext.Provider value={value}>
      {children}
    </ChatPersonContext.Provider>
  )
}
