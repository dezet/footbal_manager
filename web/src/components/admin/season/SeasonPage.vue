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
            <div>
              <code>query: {{ query }}</code>
              <datatable v-bind="$data">
              </datatable>
            </div>

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
    name: 'Panel',
    data: () => ({
      columns: [
        {
          title: 'id',
          field: 'id',
          tdClass: 'id-tag'
        },
        {
          title: 'League',
          field: 'league'
        },
        {
          title: 'Home',
          field: 'home'
        },
        {
          title: 'Away',
          field: 'away'
        },
        {
          title: 'Date',
          field: 'date'
        },
        {
          title: 'Home score',
          field: 'homeScore'
        },
        {
          title: 'Away score',
          field: 'awayScore'
        },
        {
          title: 'Played',
          field: 'played'
        }
      ],
      data: [],
      total: 0,
      query: {},
      season: {},
      timetable: [],
      showModal: false,
      current: 0
    }),
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'seasons/' + this.$route.params.id, auth.getAuthHeader()).then(response => {

        self.season = response.data
        return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
          auth.getAuthHeader())
      }).then(response => {
        self.data = response.data.map(elem => {
          return {
            id: elem.id,
            league: elem.league.name,
            home: elem.home.name,
            away: elem.away.name,
            date: elem.date,
            homeScore: elem.homeScore,
            awayScore: elem.awayScore,
            played: elem.played
          }
        })
      }).catch(e => {
        throw e
      })
      //  chujowe, ale nie dalo mi sie inaczej tego zrobic :( hax troche
      setTimeout(() => {
        $(' tr').click(function () {
          var e          = $(this).find('td.id-tag').first()
          var b          = $(e)
          var id         = e[0].innerText
          self.current   = id
          self.showModal = true
        })
      }, 500)
    },
    // methods used in template
    methods: {
      generateTimetable: function (id) {
        let self = this
        axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/generate', auth.getAuthHeader()).
              then(response => {
                //  TODO: TUTAJ SPRAWDZI RESPONSE NAJPIERW CZY SIE STWORZYLO
                return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
                  auth.getAuthHeader())
              }).then(response => {
          self.data = response.data.map(elem => {
            return {
              league: elem.league.name,
              home: elem.match.home.name,
              away: elem.match.home.away,
              date: elem.date,
              homeScore: elem.homeScore,
              awayScore: elem.awayScore,
              played: elem.played
            }
          })

        }).catch(e => {
          throw e
        })
      },
      closeModal: function () {
        this.showModal = false
      },
      updateMatch: function () {
        let params = {
          id: this.current,
          homeScore: $('input#home').val(),
          awayScore: $('input#away').val()
        }
        axios.post(this.$config.API + 'matches/' + params.id + '/score', params,
          auth.getAuthHeader()).then(response => {
          this.showModal = false
          return axios.get(this.$config.API + 'seasons/' + this.$route.params.id + '/timetable',
            auth.getAuthHeader())
        }).then(response => {
          self.data = response.data.map(elem => {
            return {
              id: elem.id,
              league: elem.league.name,
              home: elem.home.name,
              away: elem.away.name,
              date: elem.date,
              homeScore: elem.homeScore,
              awayScore: elem.awayScore,
              played: elem.played
            }
          })
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

</style>
