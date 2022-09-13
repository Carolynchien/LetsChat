import './chat.styles.css'
import { useState, useEffect, useContext } from 'react'
import { over } from 'stompjs'
//for websocket url
import SockJS from 'sockjs-client'
import { UserContext } from '../../context/user.context'
import { ChatPersonContext } from '../../context/chatperson.context'
import axios from 'axios'

const defaultUserData = {
  senderName: '',
  receiverName: '',
  message: '',
  status: ''
}

const map = new Map()
map.set('carolyn', [])
let sompClient = null
const Chat = () => {
  const { user, setUser } = useContext(UserContext)
  const { chatPerson, setChatPerson } = useContext(ChatPersonContext)
  const [userData, setUserDate] = useState(defaultUserData)
  const [privateChats, setPrivateChats] = useState(new Map())
  const [tab, setTab] = useState('')
  const [isLodaing, setIsLoading] = useState(false)
  const [chats, setChats] = useState([])
  const [conversation, setConversation] = useState([])
  const [isFirstTimeSending, setIsFirstTimeSending] = useState(false)
  const [newComingPpl, setNewComingPpl] = useState()

  console.log(user, chatPerson, tab)

  useEffect(() => {
    if (user === null && chatPerson === null) {
      const storedUser = JSON.parse(localStorage.getItem('user'))
      const storedChatPerson = JSON.parse(localStorage.getItem('likedPerson'))
      setUser(storedUser)
      setChatPerson(storedChatPerson)
      setTab(storedChatPerson.firstName)
      if (privateChats.get(storedChatPerson.firstName)) {
        privateChats.set(storedChatPerson.firstName, [])
        setPrivateChats(new Map(privateChats))
        setIsLoading(true)
        setChats(storedUser.chats)
      }
    } else {
      setTab(chatPerson.firstName)
      if (privateChats.get(chatPerson.firstName)) {
        privateChats.set(chatPerson.firstName, [])
        setPrivateChats(new Map(privateChats))
        setIsLoading(true)
      }
      setChats(user.chats)
    }

    const regisertUser = () => {
      let Sock = new SockJS(`http://localhost:8080/ws`)
      sompClient = over(Sock)
      console.log(sompClient)

      sompClient.connect({}, onConnected, onError)
    }
    regisertUser()
    console.log(chatPerson.firstName)
  }, [])

  useEffect(() => {
    const filterchatPpl = (existingChatPpl, newComingChatPpl) => {
      console.log(chats)
      console.log(newComingChatPpl)
      let newarray = existingChatPpl.filter((ppl) => {
        return ppl.firstName != newComingChatPpl.senderName
      })
      console.log(newarray)
      setChats(newarray)
    }
    filterchatPpl(chats, newComingPpl)
  }, [newComingPpl])

  console.log(chats)

  const onConnected = () => {
    sompClient.subscribe(
      `/topic/${user.firstName}/private`,
      onPrivateMessageReceived
    )
  }
  //this is where other people can send out message and we receive.

  const handleUserName = (e) => {
    const { value, name } = e.target
    setUserDate({ ...userData, [name]: value })
  }

  const handleMessage = (e) => {
    const { value, name } = e.target
    console.log(value, name)
    setUserDate({ ...userData, [name]: value })
  }

  const onError = (e) => {
    console.log(e.message)
  }

  const onPrivateMessageReceived = (payload) => {
    console.log('receving message')
    let payloadDate = JSON.parse(payload.body)

    console.log(payloadDate)

    if (privateChats.get(payloadDate.senderName)) {
      console.log(privateChats.get(payloadDate.senderName))
      privateChats.get(payloadDate.senderName).push(payloadDate)
      setPrivateChats(new Map(privateChats))
      setIsLoading(true)
    } else {
      let list = []
      list.push(payloadDate)
      privateChats.set(payloadDate.senderName, list)
      setPrivateChats(new Map(privateChats))
      setIsLoading(true)
      setNewComingPpl(payloadDate)
    }
  }

  const sendPrivateMessage = () => {
    console.log(chatPerson)
    if (sompClient) {
      let chatMessage = {
        senderName: user.firstName,
        receiverName: tab,
        message: userData.message,
        status: 'MESSAGE'
      }

      if (privateChats.get(tab)) {
        privateChats.get(tab).push(chatMessage)
        setPrivateChats(new Map(privateChats))
        setIsLoading(true)
      } else {
        let list = []
        list.push(chatMessage)
        privateChats.set(tab, list)
        setPrivateChats(new Map(privateChats))
        setIsLoading(true)
        setIsFirstTimeSending(true)
      }

      sompClient.send(`/app/private-message`, {}, JSON.stringify(chatMessage))
      setUserDate(defaultUserData)
    }
  }
  // const findHistoryMessage = async (firstName) => {
  //   const res = await axios.get(
  //     `http://localhost:8080/api/message/get/${user.firstName}/${firstName}`
  //   )
  //   const conversationData = await res.data
  //   setConversation(conversationData)
  //   console.log(conversationData)
  //   // setTab(firstName)
  //   // setChatPerson(firstName)
  // }
  // console.log(privateChats)
  // if (isFirstTimeSending) {
  //   console.log(chatPerson.imageUrl)
  // }
  const swithChatPerson = (chatperson) => {
    setTab(chatperson)
    setChatPerson({ ...chatPerson, receiverName: chatPerson })
  }

  return (
    <div className="chat-main-container">
      <div className="messager-container">
        <div className="messager-title">
          <h3>People</h3>
        </div>
        {[...privateChats.keys()].map((name, index) => (
          <div
            key={index}
            className="messengers"
            onClick={() => swithChatPerson(name)}
          >
            <div className="profile-container-like">
              {isFirstTimeSending ? (
                <img src={chatPerson.imageUrl} />
              ) : name ? (
                <img src={privateChats.get(name)[0].imageUrl} />
              ) : (
                ''
              )}
            </div>
            <p>{name}</p>
          </div>
        ))}
        {user &&
          chats.map((chat) => (
            <div
              // onClick={() => findHistoryMessage(chat.firstName)}
              className="messengers"
            >
              <div className="profile-container-like">
                <img src={chat.imageUrl} />
              </div>
              <p>{chat.firstName}</p>
            </div>
          ))}
      </div>
      <div className="chat-box">
        <div className="content-box">
          {conversation.map((con) => (
            <div
              className={`${
                con.senderName === user.firstName ? 'right' : 'left'
              } message-box`}
            >
              <p>{con.message}</p>
            </div>
          ))}

          {isLodaing
            ? [...privateChats.get(tab)].map((message, index) => (
                <p
                  className={`${
                    tab === message.senderName ? 'left' : 'right'
                  } message-box`}
                >
                  {tab === message.senderName && (
                    <div className="message">{message.senderName}</div>
                  )}

                  <div
                    className={`${
                      tab === message.senderName ? 'left' : 'right'
                    } message-content-box`}
                  >
                    <p>{message.message}</p>
                  </div>
                  {/* {tab !== message.senderName && (
                    <div className="receiver message">{message.senderName}</div>
                  )} */}
                </p>
              ))
            : ''}
        </div>
        <div className="input-box">
          <input
            type="text"
            name="message"
            value={userData.message}
            onChange={handleMessage}
          />
          <button onClick={sendPrivateMessage}>send</button>
        </div>
      </div>
    </div>
  )
}

export default Chat
