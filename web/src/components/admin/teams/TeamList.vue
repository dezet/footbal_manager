<template>
  <div class="teams">
    <div class="teams_list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Nazwa</th>
          <th>Liga</th>
        </tr>
        </thead>
        <tbody name="table-row">
          <tr v-for="team in teams" :key="team.id" v-on:click="showTeam(team.id)">
            <td :key="team.id">
            <td>{{ team.name }}</td>
            <td>{{ team.league.name }}</td>
            <td>
              <button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="teams_form_container">
      <form id="form" v-on:submit.prevent="addTeam" class="teams_form_container_form">
        <div class="form-group">
          <label for="teamName">Team name</label>
          <input id="teamName" class="form-control" type="text" v-model="newteam.name" placeholder="Name">
        </div>

        <div class="form-group">
          <label for="league">League</label>
          <select id="league" class="form-control" v-model="newteam.league">
            <option v-for="league in leagues" v-bind:value="league">{{ league.name }}</option>
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
    name: "team-list",
    // data used in template
    data: function () {
      return {
        teams: [],
        leagues: [],
        newteam: {}
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'teams', auth.getAuthHeader()).then(response => {
        self.teams = response.data
      }).catch(e => {
        throw e
      })

      self.getLeagues()
    },
    methods: {
      getLeagues: function () {
        let self = this
        axios.get(this.$config.API + 'leagues', auth.getAuthHeader()).then(response => {
          self.leagues = response.data
        }).catch(e => {
          throw e
        })
      },
      addTeam: function () {
        let self = this
        axios.post(this.$config.API + 'teams', this.newteam, auth.getAuthHeader()).then(response => {
          self.newteam = {}
          return axios.get(this.$config.API + 'teams', auth.getAuthHeader())
        }).then(response => {
          self.teams = response.data
        }).catch(e => {
          throw e
        })
      }
    }
  }
</script>

<style scoped>
  .teams_form_container {
    width: 50%;
    margin: 0 auto;
  }
</style>
