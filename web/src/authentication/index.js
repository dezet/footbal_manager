import router from '../router'
import axios from 'axios'

// URL and endpoint constants
const LOGIN_URL = 'http://localhost:8124/login'
const SIGNUP_URL = 'http://localhost:8124/players'

export default {

  // User object will let us check authentication status
  user: {
    authenticated: false
  },

  // Send a request to the login URL and save the returned JWT
  login (creds, redirect) {
    axios.post(LOGIN_URL, creds).then(
      (response) => {
        localStorage.setItem('access_token', response.headers.access_token)

        this.user.authenticated = true

        // Redirect to a specified route
        if (redirect) {
          router.push(redirect)
        }
      },
    (err) => {
      console.log(err)
      return this.user.authenticated
    })
  },

  signup (creds, redirect) {
    axios.post(SIGNUP_URL, creds).then(
      (response) => {
        localStorage.setItem('access_token', response.access_token)

        this.user.authenticated = true

        // Redirect to a specified route
        if (redirect) {
          router.push(redirect)
        }
      },
      (err) => {
        console.log(err)
      })
  },

  // To log out, we just need to remove the token
  logout () {
    localStorage.removeItem('access_token')
    this.user.authenticated = false
  },

  checkAuth () {
    let jwt = localStorage.getItem('access_token')
    this.user.authenticated = jwt !== 'undefined'
    return this.user.authenticated
  },

  // The object to be passed as a header for authenticated requests
  getAuthHeader () {
    return {
      'Authorization': 'Bearer ' + localStorage.getItem('access_token')
    }
  }
}
