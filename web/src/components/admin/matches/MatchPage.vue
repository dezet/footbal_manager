<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Informacje o meczu</h1>
          </div>
        </header>

        <div class="content" id="content">
          <button class="btn btn-success" v-on:click="showModal = true"><i class="fa fa-pencil" aria-hidden="true"></i>
            Dodaj wynik
          </button>
          <div class="information">
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">{{match.home.name}}</h6>
                  <small class="text-muted">Gospodarz</small>
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">{{match.away.name}}</h6>
                  <small class="text-muted">Gość</small>
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">{{match.league.season.name}}/{{match.league.season.year}}</h6>
                  <small class="text-muted">Sezon</small>
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">{{match.league.name}}</h6>
                  <small class="text-muted">Liga</small>
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">{{match.homeScore}} : {{match.awayScore}}</h6>
                  <small class="text-muted">Wynik (Gospodarz : Gość)</small>
                </div>
              </li>
            </ul>
          </div>
          <div class="players">
            Pilkarze przyjezdni
            <table class="table table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th></th>
              </tr>
              </thead>
              <tbody name="table-row">
              <tr v-for="player in match.away.players" :key="player.id" v-on:click="showMatch(match.id)">
                <td :key="player.id">
                <td>{{player.firstname}}</td>
                <td>{{player.lastname}}</td>
                <td>
                  <!--<button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>-->
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="players">
            Pilkarze gospodarzy
            <table class="table table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Imie</th>
                <th>Nawisko</th>
                <th></th>
              </tr>
              </thead>
              <tbody name="table-row">
              <tr v-for="player in match.home.players" :key="player.id" v-on:click="showMatch(match.id)">
                <td :key="player.id">
                <td>{{player.firstname}}</td>
                <td>{{player.lastname}}</td>
                <td>
                  <!--<button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>-->
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <modal v-if="showModal" @closeModal="closeModal()" @updateMatch="updateMatch()" v-on:show="showModal = true">
    </modal>
  </div>
</template>
<script>
  import PanelMenu from '@/components/admin/PanelMenu'
  import MatchScoreModal from '@/components/admin/season/MatchScoreModal'
  import axios from 'axios'
  import auth from '../../../authentication'

  export default {
    name: 'match-page',
    data: () => ({
      data: [],
      total: 0,
      query: {},
      match: {
        home: {players: []},
        away: {players: []}
      },
      timetable: [],
      showModal: false,
      current: 0
    }),
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'matches/' + this.$route.params.id, auth.getAuthHeader()).then(response => {
        self.match = response.data
      }).catch(e => {
        throw e
      })
    },
    // methods used in template
    methods: {
      closeModal: function () {
        this.showModal = false
      },
      updateMatch: function () {
        let self   = this
        let params = {
          id: this.$route.params.id,
          homeScore: $('input#home').val(),
          awayScore: $('input#away').val()
        }
        axios.post(this.$config.API + 'matches/' + params.id + '/score', params,
          auth.getAuthHeader()).then(response => {
          self.showModal = false
          return axios.get(this.$config.API + 'matches/' + this.$route.params.id, auth.getAuthHeader())
        }).then(response => {
          self.match = response.data
        }).catch(e => {
          throw e
        })
      }
    },
    components: {
      'panel-menu': PanelMenu,
      'modal': MatchScoreModal
    }
  }
</script>

<style scoped>
  .information {
    width: 50%;
    margin: auto;
  }

  .players {
    width: 70%;
    margin: auto;
  }
</style>
