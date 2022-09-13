import { useEffect, useState, useContext } from 'react'
import { UserContext } from '../../context/user.context'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import './sign.styles.css'
import { images } from '../../assets/sign-in-images'
import { ToggleContext } from '../../context/toggle.context'

const defautSignIn = { email: '', password: '' }

const SignIn = () => {
  const [signin, setSignin] = useState(defautSignIn)
  const [login, setLogin] = useState(defautSignIn)
  const { toggleSignIn, setToggleSignIn } = useContext(ToggleContext)
  console.log(toggleSignIn)
  const { email, password } = signin
  const { logineEmail, loginPassword } = login
  const { user, setUser } = useContext(UserContext)
  let naviagte = useNavigate()

  const checkEmail = async (e) => {
    e.preventDefault()
    const res = await axios.post(
      `http://localhost:8080/api/user-sign-up`,
      signin
    )
    console.log(res.data)
    setUser(res.data)
    if (res.data) {
      naviagte(`/userinfo`)
    }
  }

  const userLogin = async (e) => {
    e.preventDefault()
    const res = await axios.post(
      `http://localhost:8080/api/v1/auth/login`,
      login
    )
    console.log(res.data)
    const user = await res.data
    localStorage.setItem('token', JSON.stringify(res.data.token))
    localStorage.setItem('user', JSON.stringify(res.data.user))

    setUser(user.user)

    if (user) {
      naviagte(`/dashboard`)
    }
  }

  const handleChange = (e) => {
    e.preventDefault()
    const { value, name } = e.target
    console.log(value)
    setSignin({ ...signin, [name]: value })
  }
  const handleChangeLogin = (e) => {
    e.preventDefault()
    const { value, name } = e.target

    setLogin({ ...login, [name]: value })
    console.log(login)
  }

  return (
    <div className="sign-main-container">
      <div className="sign-in-background">
        {images.map((image, index) => (
          <div className={`div-box-${index + 1} div-box `}>
            <img src={image} />
          </div>
        ))}
      </div>
      <div
        className={`form-container ${toggleSignIn ? 'active' : 'disactive'}`}
      >
        <form onSubmit={userLogin} className="sign-up-box">
          <i
            class="fa-regular fa-circle-xmark"
            onClick={() => setToggleSignIn(!toggleSignIn)}
          ></i>

          <h2>GET STARTED</h2>
          <p>By clicking Log in, you agree to our terms.</p>
          <div className="form-box">
            <label> Email:</label>
            <input
              type="email"
              name="email"
              value={logineEmail}
              onChange={handleChangeLogin}
              // required
            />
          </div>
          <div className="form-box">
            <label> Password:</label>
            <input
              type="password"
              name="password"
              value={loginPassword}
              onChange={handleChangeLogin}
              // required
            />
          </div>
          <button>Log in</button>
        </form>
        <form onSubmit={checkEmail} className="sign-up-box">
          <h2>CREATE ACCOUNT</h2>
          <p>By clicking sign up, you agree to our terms.</p>
          <div className="form-box">
            <label> Email:</label>

            <input
              type="email"
              name="email"
              value={email}
              onChange={handleChange}
              // required
            />
          </div>
          <div className="form-box">
            <label> Password:</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={handleChange}
              // required
            />
          </div>
          {/* <p>Email has been taken</p> */}
          <button>Sign Up</button>
        </form>
      </div>
    </div>
  )
}

export default SignIn
