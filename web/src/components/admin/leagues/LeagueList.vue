<template>
  <div class="leagues">
    <button class="btn btn-success" v-on:click="showModal = true"><i class="fa fa-pencil" aria-hidden="true"></i>
      Dodaj ligę
    </button>
    <modal v-if="showModal" :seasons="seasons" @closeModal="closeModal()" @addLeague="addLeague()"
           v-on:show="showModal = true" />
    <div class="leagues_list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Season</th>
          <th></th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="league in leagues" :key="league.id" v-on:click="showLeague(league.id)">
          <td :key="league.id">
          <td>{{league.name}}</td>
          <td>{{league.season.name}}</td>
          <td>
            <button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import auth from '../../../authentication'
  import LeagueModal from '@/components/admin/leagues/LeagueModal'

  export default {
    name: "league-list",
    components: {
      'modal': LeagueModal
    },
    // data used in template
    data: function () {
      return {
        showModal: false,
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
      closeModal: function () {
        this.showModal = false
      },
      addLeague: function () {
        this.closeModal()
        let self = this
        this.newleague = {
          name: $('input#name').val(),
          seasonId: $('select#season').val()
        }
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
