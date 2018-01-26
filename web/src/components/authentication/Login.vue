<template>
  <div class="wrapper">
    <div class="container">
      <div class="login-form">
        <h1>Logowanie</h1>

        <div class="alert alert-danger" v-if="error">
          <p>{{ error }}</p>
        </div>

        <form>
          <div class="form-group">
            <label for="username">Login: </label>
            <input
              id="username"
              type="text"
              class="form-control"
              placeholder="Podaj login"
              v-model="credentials.username"
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
          <button class="btn btn-primary" @click="submit()">Zaloguj</button>
        </form>
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
          username: '',
          password: ''
        },
        error: ''
      }
    },
    methods: {
      submit () {
        var credentials = {
          username: this.credentials.username,
          password: this.credentials.password
        }
        auth.login(credentials).then(
          (response) => {
            this.$router.push('/panel')
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

  .login-form {
    width: 100%;
  }

  .login-form h1 {
    text-align: center;
  }
</style>
