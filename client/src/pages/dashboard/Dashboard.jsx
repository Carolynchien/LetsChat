import './dashboard.styles.css'
import { UserContext } from '../../context/user.context'
import { useContext, useEffect, useState } from 'react'
import axios from 'axios'
import TinderCard from 'react-tinder-card'
import { useNavigate } from 'react-router-dom'
import authHeader from '../../services/auth-header'
import { ChatPersonContext } from '../../context/chatperson.context'
import { ToggleContext } from '../../context/toggle.context'
const defaultMatchPpl = { firstName: '', personId: '', imageUrl: '' }

const DashBoard = () => {
  const { user, setUser } = useContext(UserContext)

  const { chatPerson, setChatPerson } = useContext(ChatPersonContext)
  const { toggleSignIn, setToggleSignIn } = useContext(ToggleContext)
  const [likedPeople, setLikedPeople] = useState([])
  const [direction, setDirection] = useState('')
  const [matchppl, setMathppl] = useState(defaultMatchPpl)
  const [matchResults, setMatchResults] = useState([])
  console.log(user)

  let navigate = useNavigate()

  useEffect(() => {
    console.log('hello')
    const storedUser = JSON.parse(localStorage.getItem('user'))
    console.log(storedUser)
    setUser(storedUser)
  }, [])

  console.log(user)

  useEffect(() => {
    const findMatch = async () => {
      const matches = await axios.get(
        `http://localhost:8080/api/user/matches?interest_gender=${
          user ? user.genderInterest : ''
        }`,
        { headers: authHeader() }
      )
      setLikedPeople(user.likes)
      const matcheRes = await matches.data
      console.log(user.likes.length)
      const likedPpl = user.likes
      console.log(likedPpl)

      if (likedPpl.length > 0) {
        const afterFilter = await filterAlreadyMatchedPpl(matcheRes, likedPpl)
        console.log(afterFilter)
        setMatchResults(afterFilter)
      } else {
        setMatchResults(matcheRes)
      }
    }
    findMatch()
  }, [user])

  useEffect(() => {
    const findlikes = async () => {
      const like = await axios.get(
        `http://localhost:8080/api/like/${user.id}`,
        { headers: authHeader() }
      )
      console.log(like)
    }
    findlikes()
  }, [user])

  const filterAlreadyMatchedPpl = (matchResult, likedPpl) => {
    return matchResult.filter((person) => {
      let existing = !likedPpl.find((e) => {
        return e.personId === person.id
      })
      console.log(existing)
      return existing
    })
  }

  const swiped = async (direction, ppl) => {
    console.log(ppl)
    setDirection(direction)
    console.log(ppl.firstName)

    if (direction === 'right') {
      const liks = {
        firstName: ppl.firstName,
        personId: ppl.id,
        imageUrl: ppl.imageUrl
      }

      console.log(liks)
      const likeRes = await axios.post(
        `http://localhost:8080/api/user/${user.id}/addinglikeperson`,
        liks
      )
      const updatedUser = likeRes.data
      setUser(updatedUser)
      setLikedPeople(user.likes)
    }
  }

  const outOfFrame = (ppl) => {
    console.log(ppl + ' left the screen!')
    console.log(direction)
  }

  const showlikePeople = async () => {
    const res = await axios.get(`http://localhost:8080/api/like/${user.id}`)
    console.log(res.data)
  }

  const startChat = (person) => {
    setChatPerson(person)
    localStorage.setItem('likedPerson', JSON.stringify(person))
    navigate(`/chat`)
  }
  const unmatchPerson = async (index, likedPersonId) => {
    console.log(user.id, likedPersonId)
    const res = await axios.delete(
      `http://localhost:8080/api/like/delete/user/${user.id}/like/${likedPersonId}`
    )
    console.log(res.data)
    const list = [...likedPeople]
    list.splice(index, 1)
    setLikedPeople(list)
  }

  return (
    <div className="dashboard-main">
      <div className="swipe-section">
        {matchResults &&
          matchResults.map((ppl) => (
            <TinderCard
              className="swipe"
              key={ppl.name}
              preventSwipe={['up', 'down']}
              onSwipe={(dir) => swiped(dir, ppl)}
              onCardLeftScreen={() => outOfFrame(ppl)}
            >
              <div className="swipe-image-box">
                <img src={ppl.imageUrl} />
                <div className="description">
                  <span>
                    {ppl.firstName} <span> {` ${ppl.age} `}</span>
                  </span>
                  <h5>{ppl.occupation}</h5>
                </div>
              </div>
            </TinderCard>
          ))}
      </div>
      <div className="like-section">
        <div className="option">
          <div>Like</div>
          <div>Match</div>
        </div>

        <div>
          {user
            ? likedPeople.map((likedPerson, index) => (
                <div className="like-icon-box">
                  <div className="profile-container-like">
                    {user ? <img src={likedPerson.imageUrl} /> : ''}
                  </div>
                  <p className="person-name">{likedPerson.firstName}</p>
                  <div>
                    {likedPerson.status === 'match' ? (
                      <i class="fa-solid fa-heart match-heart"></i>
                    ) : (
                      ''
                    )}
                    <i
                      class="fa-solid fa-message "
                      onClick={() => startChat(likedPerson)}
                    ></i>
                    <div className="unmatch-box">
                      <span>Unmatch</span>
                      <i
                        class="corss fa-regular fa-circle-xmark"
                        onClick={() => unmatchPerson(index, likedPerson.id)}
                      ></i>
                    </div>
                  </div>
                </div>
              ))
            : ''}
        </div>
      </div>
    </div>
  )
}

export default DashBoard
