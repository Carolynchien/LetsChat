import { Outlet } from 'react-router-dom'
import { Fragment, useContext } from 'react'
import './nav.styles.css'
import { UserContext } from '../context/user.context'
import { ToggleContext } from '../context/toggle.context'
import { useNavigate } from 'react-router-dom'

const NavBar = () => {
  const { user, setUser } = useContext(UserContext)
  const { toggleSignIn, setToggleSignIn } = useContext(ToggleContext)
  console.log(user)
  let navigate = useNavigate()
  const logout = () => {
    setTimeout(() => {
      navigate(`/`)
      setUser(null)
    }, 500)
  }
  return (
    <Fragment>
      <header>
        <nav className="nav-main">
          <div className="logo-box">
            <h1>Let's</h1>
            <i className="fa-regular fa-heart logo"></i>
            <i className="fa-regular fa-comments"></i>
            <i className="fa-regular fa-heart logo"></i>
            <h1>Chat</h1>
          </div>

          {/* <div className="likes-box">
            <i className="fa-regular fa-heart heart"></i>
            <i className="fa-regular fa-heart heart"></i>
          </div> */}
          <div className="list-container">
            {user ? (
              <div className="profile-container">
                <img src={user.imageUrl} />
              </div>
            ) : (
              ''
            )}
            <ul
              className="lists"
              onClick={() => setToggleSignIn(!toggleSignIn)}
            >
              {user ? <li onClick={logout}>Log Out</li> : ''}
              {user ? '' : <li>Sign In</li>}
            </ul>
          </div>
        </nav>
      </header>
      <Outlet />
    </Fragment>
  )
}

export default NavBar
