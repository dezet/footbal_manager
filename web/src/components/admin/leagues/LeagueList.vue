<template>
  <div class="leagues">
    <div class="leagues_list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Season</th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="league in leagues" :key="league.id" v-on:click="showLeague(league.id)">
          <td :key="league.id">
          <td>{{league.name}}</td>
          <td>{{league.season.name}}</td>
          <td>
            <button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            <br/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="leagues_form_container">
      <form id="form" v-on:submit.prevent="addLeague" class="leagues_form_container_form">
        <div class="form-group">
          <label for="leagueName">League name</label>
          <input id="leagueName" class="form-control" type="text" v-model="newleague.name" placeholder="Name">
        </div>

        <div class="form-group">
          <label for="season">Season</label>
          <select id="season" class="form-control" v-model="newleague.season">
            <option v-for="season in seasons" v-bind:value="season">{{ season.name }}</option>
          </select>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
      </form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import auth from '../../../authentication'
  export default {
    name: "league-list",
    // data used in template
    data: function () {
      return {
        leagues: [],
        seasons: [],
        newleague: {}
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'leagues', auth.getAuthHeader()).then(response => {
        self.leagues = response.data
        console.log(response.data)
      }).catch(e => {
        throw e
      })

      self.getSeasons()
    },
    methods: {
      getSeasons: function () {
        let self = this
        axios.get(this.$config.API + 'seasons', auth.getAuthHeader()).then(response => {
          self.seasons = response.data
          console.log(response.data)
        }).catch(e => {
          throw e
        })
      },
      addLeague: function () {
        let self = this
        axios.post(this.$config.API + 'leagues', this.newleague, auth.getAuthHeader()).then(response => {
          self.newleague = {}
          return axios.get(this.$config.API + 'leagues', auth.getAuthHeader())
        }).then(response => {
          self.leagues = response.data
        }).catch(e => {
          throw e
        })
      }
    }
  }
</script>

<style scoped>
  .leagues_form_container {
    width: 50%;
    margin: 0 auto;
  }
</style>
