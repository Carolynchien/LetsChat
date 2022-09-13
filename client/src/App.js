import logo from './logo.svg'
import './App.css'
import UserInfo from './pages/User-info/User-info'
import SignIn from './pages/signin/SignIn'
import NavBar from './components/Nav'
import Chat from './pages/chat/chat'
import { Routes, Route } from 'react-router-dom'
import DashBoard from './pages/dashboard/Dashboard'

function App() {
  return (
    <div className="App">
      <NavBar></NavBar>
      <Routes>
        {/* <Route index path="/signin" element={<SignIn />} /> */}
        <Route path="/" element={<SignIn />} />
        <Route path="/userinfo" element={<UserInfo />} />
        <Route path="/chat" element={<Chat />} />
        <Route path="/dashboard" element={<DashBoard />} />
      </Routes>
    </div>
  )
}

export default App
