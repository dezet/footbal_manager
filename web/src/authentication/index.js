import axios from 'axios'

// URL and endpoint constants
const LOGIN_URL  = 'http://localhost:8124/login'
const SIGNUP_URL = 'http://localhost:8124/players'

export default class Authentication {
  static login (creds) {
    return new Promise((resolve, reject) => {
      axios.post(LOGIN_URL, creds).then(
        (response) => {
          localStorage.setItem('access_token', response.headers.access_token)
          resolve(true)
        },
        (err) => {
          reject(err)
        })
    })
  }

  static signup (creds) {
    return new Promise((resolve, reject) => {
      axios.post(SIGNUP_URL, creds).then(
        (response) => {
          localStorage.setItem('access_token', response.access_token)
          resolve(true)
        },
        (err) => {
          reject(err)
        })
    })
  }

  static logout () {
    localStorage.removeItem('access_token')
  }

  static checkAuth () {
    let jwt = localStorage.getItem('access_token')
    return (jwt !== 'undefined' && jwt !== null)
  }

  static getAuthHeader () {
    return {
      headers: {
        Authorization: localStorage.getItem('access_token') !== 'undefined'
          ? `Bearer ${localStorage.getItem('access_token')}`
          : ''
      }
    }
  }
}
