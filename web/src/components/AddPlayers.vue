<template>
  <div class="players">
    <div class="players__list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="player in players" :key="player.id">
          <td :key="player.id">
          <td>{{player.firstname}}</td>
          <td>{{player.lastname}}</td>
          <td>
            <button v-on:click="deletePlayer(player)">Delete</button>
            <br/>
            <button>Edit </button>
            <br/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="players_form_container">
      <form id="form" v-on:submit.prevent="addPlayer" class="players_form_container__form">
        <input type="text" v-model="newPlayer.firstname" placeholder="Firstname">
        <input type="text" v-model="newPlayer.lastname" placeholder="Lastname">
        <input type="submit" value="Save">
      </form>
    </div>
    <!-- errory tez by dobrze było zrobić XD -->
    <!--<ul class="errors">-->
    <!--<li v-show="!validation.name">Name cannot be empty.</li>-->
    <!--<li v-show="!validation.email">Please provide a valid email address.</li>-->
    <!--</ul>-->
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'AddPlayers',
    // data used in template
    data: function () {
      return {
        players: [],
        newPlayer: {}
      }
    },
    // initial data load
    created: function () {
      axios.get(this.$config.API + 'players').then(response => {
        this.players = response.data
      }).catch(e => {
        throw e
      })
    },
    // methods used in template
    methods: {
      addPlayer: function () {
        let self = this
        axios.post(this.$config.API + 'players', this.newPlayer).then(response => {
          console.log(self.players);
          self.players.push(this.newPlayer)
          console.log(self.players);
          self.newPlayer = {}
        }).catch(e => {
          throw e
        })
      },
      deletePlayer: function (player) {
        let self = this
        axios.delete(this.$config.API + 'players/' + player.id).then(response => {
          console.log(response)
          self.players = this.players.filter(p => {
            return p !== player
          })
        })
      }
    }
  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

  .players {
    background: #F7F7F7;
    min-width: 360px;
    margin: auto;
    min-height: 360px;
    flex-direction: column;
    align-items: center;
    align-content: center;
    flex: 1;
    display: flex;
  }

  .players_form_container__form {
    font: 95% Arial, Helvetica, sans-serif;
    max-width: 400px;
    margin: 10px auto;
    padding: 16px;
    background: #F7F7F7;
  }

  .players_form_container__form h1 {
    background: #43D1AF;
    padding: 20px 0;
    font-size: 140%;
    font-weight: 300;
    text-align: center;
    color: #fff;
    margin: -16px -16px 16px -16px;
  }

  .players_form_container__form input[type="text"],
  .players_form_container__form input[type="date"],
  .players_form_container__form input[type="datetime"],
  .players_form_container__form input[type="email"],
  .players_form_container__form input[type="number"],
  .players_form_container__form input[type="search"],
  .players_form_container__form input[type="time"],
  .players_form_container__form input[type="url"],
  .players_form_container__form textarea,
  .players_form_container__form select {
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    background: #fff;
    margin-bottom: 4%;
    border: 1px solid #ccc;
    padding: 3%;
    color: #555;
    font: 95% Arial, Helvetica, sans-serif;
  }

  .players_form_container__form input[type="text"]:focus,
  .players_form_container__form input[type="date"]:focus,
  .players_form_container__form input[type="datetime"]:focus,
  .players_form_container__form input[type="email"]:focus,
  .players_form_container__form input[type="number"]:focus,
  .players_form_container__form input[type="search"]:focus,
  .players_form_container__form input[type="time"]:focus,
  .players_form_container__form input[type="url"]:focus,
  .players_form_container__form textarea:focus,
  .players_form_container__form select:focus {
    box-shadow: 0 0 5px #43D1AF;
    padding: 3%;
    border: 1px solid #43D1AF;
  }

  .players_form_container__form input[type="submit"],
  .players_form_container__form input[type="button"] {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    padding: 3%;
    background: #43D1AF;
    border-bottom: 2px solid #30C29E;
    border-top-style: none;
    border-right-style: none;
    border-left-style: none;
    color: #fff;
  }

  .players_form_container__form input[type="submit"]:hover,
  .players_form_container__form input[type="button"]:hover {
    background: #2EBC99;
  }

  .players_form_container {

  }

  .players__list {
    width: 80%;
    padding-top: 10vh;
    min-height: 30vh;
  }
</style>
