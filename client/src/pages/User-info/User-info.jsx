import { useContext, useState } from 'react'
import axios from 'axios'
import './user.styles.css'
import { UserContext } from '../../context/user.context'
import { useNavigate } from 'react-router-dom'
const defaultUserInput = {
  firstName: '',
  lastName: '',
  email: '',
  dob: '',
  gender: '',
  genderInterest: '',
  imageUrl: '',
  about: '',
  occupation: ''
}
let formData = new FormData()
const UserInfo = () => {
  const [userinput, setUserInput] = useState(defaultUserInput)
  const [formdata1, setFormdata1] = useState()
  const { user, setUser } = useContext(UserContext)
  let naviagte = useNavigate()
  const {
    firstName,
    lastName,
    email,
    dob,
    gender,
    genderInterest,
    imageUrl,
    about,
    occupation
  } = userinput

  const submitform = async (e) => {
    e.preventDefault()

    try {
      const newUser = await axios.put(
        `http://localhost:8080/api/user-sign-up/${user.id}`,
        userinput
      )

      console.log(newUser.data)
      if (newUser.data) {
        setUser(newUser.data)
        setUserInput(defaultUserInput)
        naviagte(`/dashboard`)
      }
    } catch (e) {
      console.log(e.message)
    }
    // console.log(newUser.response.status.message)
  }

  const handleChange = (e) => {
    e.preventDefault()
    const { value, name } = e.target
    console.log(value)
    setUserInput({ ...userinput, [name]: value })
  }

  const handleImage = async (e) => {
    const formData1 = new FormData()
    formData1.append('file', e.target.files[0])
    formData1.append('upload_preset', 'thatschat')

    const res = await axios.post(
      `https://api.cloudinary.com/v1_1/dbu07di7k/image/upload`,
      formData1
    )
    const url = await res.data.url
    console.log(url)
    setUserInput({ ...userinput, imageUrl: url })
  }

  console.log(userinput)

  return (
    <div className="userinfo-main-container">
      <h1>user info</h1>
      <form onSubmit={submitform} className="form-container">
        <div className="text-container">
          <label> First Name:</label>
          <input
            type="text"
            name="firstName"
            value={firstName}
            onChange={handleChange}
            required
          />
          <label> Last Name:</label>

          <input
            type="text"
            name="lastName"
            value={lastName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="text-container">
          <label> Email:</label>
          <input
            type="email"
            name="email"
            value={email}
            onChange={handleChange}
            required
          />
          <label>Birthday:</label>

          <input
            type="date"
            name="dob"
            value={dob}
            onChange={handleChange}
            required
          ></input>
        </div>
        <label> Gender :</label>
        <div className="radio-btn">
          <input
            type="radio"
            name="gender"
            value="male"
            onChange={handleChange}
            id="man"
            checked={gender === 'male' ? true : false}
          />
          <label htmlFor="man"> Man</label>
          <input
            type="radio"
            name="gender"
            value="femal"
            onChange={handleChange}
            id="women"
            checked={gender === 'femal' ? true : false}
          />
          <label htmlFor="women"> Women</label>
        </div>
        <label />
        I'm interested in :
        <div className="radio-btn">
          <input
            type="radio"
            name="genderInterest"
            value="male"
            onChange={handleChange}
            id="interest-man"
            checked={genderInterest === 'male' ? true : false}
          />
          <label htmlFor="interest-man"> Man</label>
          <input
            type="radio"
            name="genderInterest"
            value="femal"
            onChange={handleChange}
            id="interest-woman"
            checked={genderInterest === 'femal' ? true : false}
          />
          <label htmlFor="interest-woman"> Women</label>
        </div>
        <div className="text-container">
          <label />
          Occupation:
          <input
            type="text"
            name="occupation"
            value={occupation}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label />
          About me:
          <input
            type="textarea"
            name="about"
            value={about}
            onChange={handleChange}
            required
          />
          <input type="file" name="imageUrl" onChange={handleImage} />
        </div>
        <button>Submit</button>
      </form>

      {user ? <h1>{user.firstName}</h1> : ''}
      {user ? <img src={user.imageUrl} /> : ''}
    </div>
  )
}
export default UserInfo
