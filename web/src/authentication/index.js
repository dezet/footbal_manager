import axios from 'axios'

// URL and endpoint constants
const LOGIN_URL  = 'http://localhost:8124/login'
const SIGNUP_URL = 'http://localhost:8124/players'

export default class Authentication {
  static login (creds) {
    return new Promise((resolve, reject) => {
      axios.post(LOGIN_URL, creds).then(
        (response) => {
          console.log(response)
          localStorage.setItem('access_token', response.headers.access_token)
          localStorage.setItem('is_admin', response.headers.is_admin)
          localStorage.setItem('username', response.headers.username)
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
          console.log(response)
          localStorage.setItem('access_token', response.access_token)
          localStorage.setItem('is_admin', response.headers.is_admin)
          localStorage.setItem('username', response.headers.username)
          resolve(true)
        },
        (err) => {
          reject(err)
        })
    })
  }

  static logout () {
    localStorage.removeItem('access_token')
    localStorage.removeItem('is_admin')
    localStorage.removeItem('username')
  }

  static checkAuth () {
    let jwt = localStorage.getItem('access_token')
    return (jwt !== 'undefined' && jwt !== null)
  }

  static isAdmin () {
    let jwt = localStorage.getItem('is_admin')
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

  static getUsername () {
    return localStorage.getItem('username')
  }
}
