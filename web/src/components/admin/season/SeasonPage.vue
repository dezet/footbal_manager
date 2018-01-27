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
          <div>
            <button v-on:click="generateTimetable(season)" class="btn-success">Generuj terminarz meczów</button>
          </div>
          <div class="matches">
            <datatable v-bind="$data">
            </datatable>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import PanelMenu from '@/components/admin/PanelMenu'
  import MatchScoreModal from '@/components/admin/season/MatchScoreModal'
  import axios from 'axios'
  import auth from '../../../authentication'

  export default {
    name: 'season-page',
    data: () => ({
      columns: [
        {
          title: 'id',
          field: 'id',
          tdClass: 'id-tag',
          sortable: true
        },
        {
          title: 'League',
          field: 'league',
          sortable: true
        },
        {
          title: 'Home',
          field: 'home',
          sortable: true
        },
        {
          title: 'Away',
          field: 'away',
          sortable: true
        },
        {
          title: 'Date',
          field: 'date',
          sortable: true
        },
        {
          title: 'Home score',
          field: 'homeScore',
          sortable: true
        },
        {
          title: 'Away score',
          field: 'awayScore',
          sortable: true
        },
        {
          title: 'Played',
          field: 'played',
          sortable: true
        }
      ],
      data: [],
      query: {},
      total: 0,
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
      setTimeout(() => {
        $(' tr').click(function () {
          var e        = $(this).find('td.id-tag').first()
          var b        = $(e)
          var id       = e[0].innerText
          self.current = id
          self.$router.push('/panel/matches/' + id)
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
    },
    components: {
      'panel-menu': PanelMenu,
      'modal': MatchScoreModal
    }
  }
</script>

<style scoped>
  .content {
    display: flex;
    height: 100%;
    width: 100%;
    align-content: center;
    align-items: center;
    flex-direction: column;
  }

  .matches {
    width: 100%;
  }
</style>
