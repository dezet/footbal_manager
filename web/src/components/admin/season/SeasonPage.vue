<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Informacje o sezonie</h1>
          </div>
        </header>

        <div class="content" id="content">
          {{season.id}}
          {{season.name}}
          {{season.year}}
          {{season.open}}
          <button v-on:click="generateTimetable(season)" class="btn-success">Generuj timetable</button>


          <br>
          <div>
            <table class="table table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>League</th>
                <th>Home</th>
                <th>Away</th>
                <th>Date</th>
                <th>Home score</th>
                <th>Away score</th>
                <th>Played</th>
              </tr>
              </thead>
              <tbody name="table-row">
              <tr v-for="match in timetable" :key="match.id">
                <td :key="match.id">
                <td>{{match.league.name}}</td>
                <td>{{match.home.name}}</td>
                <td>{{match.away.name}}</td>
                <td>{{match.date}}</td>
                <td>{{match.homeScore}}</td>
                <td>{{match.awayScore}}</td>
                <td>{{match.played}}</td>

              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  import PanelMenu from '@/components/admin/PanelMenu'
  import axios from 'axios'
  import auth from '../../../authentication'

  export default {
    name: 'Panel',
    data: function () {
      return {
        season: {},
        timetable: []
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'seasons/' + this.$route.params.id, auth.getAuthHeader()).then(response => {
        self.season = response.data
        return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
          auth.getAuthHeader())
      }).then(response => {
        self.timetable = response.data
      }).catch(e => {
        throw e
      })
    },
    // methods used in template
    methods: {
      generateTimetable: function (id) {
        let self = this
        axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/generate', auth.getAuthHeader()).
              then(response => {
                //TODO: TUTAJ SPRAWDZI RESPONSE NAJPIERW CZY SIE STWORZYLO
                return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
                  auth.getAuthHeader())
              }).then(response => {
          self.timetable = response
        }).catch(e => {
          throw e
        })
      }
    },
    components: {
      'panel-menu': PanelMenu
    }
  }
</script>

<style scoped>

</style>
