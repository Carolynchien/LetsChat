export default function authHeader() {
  const token = JSON.parse(localStorage.getItem('token'))
  if (token) {
    // for Node.js Express back-end
    console.log(token)
    return { Authorization: 'Bearer ' + token }
  } else {
    return {}
  }
}
