<template>
  <div class="seasons">
    <div class="seasons__list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Year</th>
          <th>Open</th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="season in seasons" :key="season.id" v-on:click="showSeason(season.id)">
          <td :key="season.id">
          <td>{{season.name}}</td>
          <td>{{season.year}}</td>
          <td>{{season.open}}</td>
          <td>
            <button v-on:click="closeseason(season)" class="btn-default">Close</button>
            <br/>
            <button class="btn-default">Edit</button>
            <br/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="seasons_form_container">
      <form id="form" v-on:submit.prevent="addseason" class="seasons_form_container__form">
        <input type="text" v-model="newseason.name" placeholder="Name">
        <input type="number" v-model="newseason.year" placeholder="Year">
        <input type="submit" value="Save">
      </form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import auth from '../../../authentication'
  export default {
    name: 'season-list',
    // data used in template
    data: function () {
      return {
        seasons: [],
        newseason: {}
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'seasons', auth.getAuthHeader()).then(response => {
        self.seasons = response.data
      }).catch(e => {
        throw e
      })
    },
    // methods used in template
    methods: {
      showSeason: function (id) {
        this.$router.push({path: '/panel/seasons/' + id})
      },
      addseason: function () {
        let self = this
        axios.post(this.$config.API + 'seasons', this.newseason, auth.getAuthHeader()).then(response => {
          self.newseason = {}
          return axios.get(this.$config.API + 'seasons', auth.getAuthHeader())
        }).then(response => {
          self.seasons = response.data
        }).catch(e => {
          throw e
        })
      },
      closeseason: function (season) {
        let self = this
        axios.patch(this.$config.API + 'seasons/' + season.id + '/close', auth.getAuthHeader()).then(response => {
          console.log(response)
          self.seasons = this.seasons.filter(p => {
            return p !== season
          })
        })
      }
    }
  }
</script><!-- Add "scoped" attribute to limit CSS to this component only -->
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

  .seasons {
    background: #F7F7F7;
    min-width: 360px;
    margin: auto;
    min-height: 360px;
    flex-direction: column;
    align-items: center;
    align-content: center;
    flex: 1;
    display: flex;
    width: 100%;
    height: 100%;
  }

  .seasons_form_container__form {
    font: 95% Arial, Helvetica, sans-serif;
    max-width: 400px;
    margin: 10px auto;
    padding: 16px;
    background: #F7F7F7;
  }

  .seasons_form_container__form h1 {
    background: #43D1AF;
    padding: 20px 0;
    font-size: 140%;
    font-weight: 300;
    text-align: center;
    color: #fff;
    margin: -16px -16px 16px -16px;
  }

  .seasons_form_container__form input[type="text"],
  .seasons_form_container__form input[type="date"],
  .seasons_form_container__form input[type="datetime"],
  .seasons_form_container__form input[type="email"],
  .seasons_form_container__form input[type="number"],
  .seasons_form_container__form input[type="search"],
  .seasons_form_container__form input[type="time"],
  .seasons_form_container__form input[type="url"],
  .seasons_form_container__form textarea,
  .seasons_form_container__form select {
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

  .seasons_form_container__form input[type="text"]:focus,
  .seasons_form_container__form input[type="date"]:focus,
  .seasons_form_container__form input[type="datetime"]:focus,
  .seasons_form_container__form input[type="email"]:focus,
  .seasons_form_container__form input[type="number"]:focus,
  .seasons_form_container__form input[type="search"]:focus,
  .seasons_form_container__form input[type="time"]:focus,
  .seasons_form_container__form input[type="url"]:focus,
  .seasons_form_container__form textarea:focus,
  .seasons_form_container__form select:focus {
    box-shadow: 0 0 5px #43D1AF;
    padding: 3%;
    border: 1px solid #43D1AF;
  }

  .seasons_form_container__form input[type="submit"],
  .seasons_form_container__form input[type="button"] {
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

  .seasons_form_container__form input[type="submit"]:hover,
  .seasons_form_container__form input[type="button"]:hover {
    background: #2EBC99;
  }

  .seasons_form_container {

  }

  .seasons__list {
    width: 80%;
    padding-top: 10vh;
    min-height: 30vh;
  }
</style>
