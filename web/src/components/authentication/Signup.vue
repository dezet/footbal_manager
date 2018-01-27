<template>
  <div class="wrapper">
    <div class="container">
      <div class="signup-form">
        <h1>Rejestracja</h1>

        <div class="alert alert-danger" v-if="error">
          <p>{{ error }}</p>
        </div>

        <form @keyup.enter="submit()">
          <div class="form-group">
            <label for="firstName">Imię: </label>
            <input
              id="firstName"
              type="text"
              class="form-control"
              placeholder="Podaj imię"
              v-model="credentials.firstname"
            >
          </div>

          <div class="form-group">
            <label for="lastName">Nazwisko: </label>
            <input
              id="lastName"
              type="text"
              class="form-control"
              placeholder="Podaj nazwisko"
              v-model="credentials.lastname"
            >
          </div>

          <div class="form-group">
            <label for="username">Nazwa użytkownika: </label>
            <input
              id="username"
              type="text"
              class="form-control"
              placeholder="Podaj nazwę użytkownika"
              v-model="credentials.username"
            >
          </div>

          <div class="form-group">
            <label for="email">Adres email: </label>
            <input
              id="email"
              type="text"
              class="form-control"
              placeholder="Podaj adres email"
              v-model="credentials.email"
            >
          </div>

          <div class="form-group">
            <label for="password">Hasło: </label>
            <input
              id="password"
              type="password"
              class="form-control"
              placeholder="Podaj hasło"
              v-model="credentials.password"
            >
          </div>
        </form>
        <button class="btn btn-primary" @click="submit()">Zarejestruj się</button>
      </div>
    </div>
  </div>
</template>

<script>
  import auth from '../../authentication'

  export default {
    data () {
      return {
        // We need to initialize the component with any
        // properties that will be used in it
        credentials: {
          firstname: '',
          lastname: '',
          email: '',
          username: '',
          password: ''
        },
        error: ''
      }
    },
    methods: {
      submit () {
        var credentials = {
          firstname: this.credentials.firstname,
          email: this.credentials.email,
          lastname: this.credentials.lastname,
          username: this.credentials.username,
          password: this.credentials.password
        }
        auth.signup(credentials).then(
          (response) => {
            this.$router.push('/login')
          },

          (err) => {
            console.log(err)
            this.error = 'Authentication failed!'
          })
      }
    }
  }
</script>

<style scoped>
  .wrapper {
    height: 100vh;
  }

  .wrapper .container {
    display: flex;
    align-items: center;
    height: 100%;
    width: 50%;
    margin: 0 auto;
  }

  .signup-form {
    width: 100%;
  }

  .signup-form h1 {
    text-align: center;
  }
</style>
