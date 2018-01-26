<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Raport sezonu</h1>
          </div>
        </header>

        <div class="content" id="content">
          <table class="table">
            <thead>
            <tr>
              <th>Remisy</th>
              <th>Wygrane u siebie</th>
              <th>Przegrane u siebie</th>
              <th>Wygrane na wyjeździe</th>
              <th>Przegrane na wyjeździe</th>
              <th>Bramki u siebie</th>
              <th>Bramki na wyjeździe</th>
            </tr>
            </thead>
            <tr style="text-align: center">
              <td>{{rapport.draws}}</td>
              <td>{{rapport.homeWins}}</td>
              <td>{{rapport.homeLosses}}</td>
              <td>{{rapport.awayWins}}</td>
              <td>{{rapport.awayLoses}}</td>
              <td>{{rapport.homeGoals}}</td>
              <td>{{rapport.awayGoals}}</td>
            </tr>
          </table>

          <table class="table table-striped">
            <thead>
            <tr>
              <th>#</th>
              <th>Liga</th>
              <th>Data meczu</th>
              <th>Gospodarze</th>
              <th></th>
              <th>:</th>
              <th></th>
              <th>Goście</th>
            </tr>
            </thead>
            <tbody name="table-row">
            <tr v-for="match in matches" :key="match.id">
              <td>{{match.id}}</td>
              <td>{{match.league.name}}</td>
              <td>{{match.date}}</td>
              <td>{{match.home.name}}</td>
              <td>{{match.homeScore}}</td>
              <td>:</td>
              <td>{{match.awayScore}}</td>
              <td>{{match.away.name}}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import auth from '../../../authentication'
  import PanelMenu from '@/components/admin/PanelMenu'

  export default {
    name: 'season-report',
    components: {
      'panel-menu': PanelMenu
    },
    data: function () {
      return {
        rapport: {},
        matches: []
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/rapport', auth.getAuthHeader()).then(response => {
        self.rapport = response.data
        return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
          auth.getAuthHeader())
      }).then(response => {
        self.matches = response.data
      }).catch(e => {
        throw e
      })
    }
  }
</script>

<style scoped>

</style>
