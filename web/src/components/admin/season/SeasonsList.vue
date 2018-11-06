<template>
  <div class="seasons">
    <button v-if="isAdmin()" class="btn btn-success" v-on:click="showModal = true"><i class="fa fa-pencil"
                                                                                      aria-hidden="true"></i>
      Dodaj sezon
    </button>
    <modal v-if="showModal" :seasons="seasons" @closeModal="closeModal()" @addSeason="addseason()"
           v-on:show="showModal = true" />
    <div class="seasons__list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Nazwa</th>
          <th>Rok</th>
          <th>Otwarty</th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="season in seasons" :key="season.id">
          <td :key="season.id">
          <td v-on:click="showSeason(season)">{{season.name}}</td>
          <td v-on:click="showSeason(season)">{{season.year}}</td>
          <td v-on:click="showSeason(season)">{{season.open ? 'tak' : 'nie'}}</td>
          <td>
            <button v-if="season.open && isAdmin()" v-on:click="closeseason(season.id)" class="btn btn-danger">Zamknij
            </button>
            <button v-if="!season.open && isAdmin()" v-on:click="showRapport(season.id)" class="btn btn-info">Raport
            </button>
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
  import SeasonModal from '@/components/admin/season/SeasonModal'

  export default {
    name: 'seasons-list',
    components: {
      'modal': SeasonModal
    },
    // data used in template
    data: function () {
      return {
        showModal: false,
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
      showSeason: function (season) {
        if (season.open && auth.isAdmin()) {
          this.$router.push({path: '/panel/seasons/' + season.id})
        }
      },
      closeModal: function () {
        this.showModal = false
      },
      addseason: function () {
        this.closeModal()
        let self = this
        this.newseason = {
          name: $('input#name').val(),
          year: $('input#year').val(),
          baseSeasonId: $('select#season').val()
        }
        axios.post(this.$config.API + 'seasons', this.newseason, auth.getAuthHeader()).then(response => {
          self.newseason = {}
          return axios.get(this.$config.API + 'seasons', auth.getAuthHeader())
        }).then(response => {
          self.seasons = response.data
        }).catch(e => {
          throw e
        })
      },
      closeseason: function (id) {
        let self = this
        axios.patch(this.$config.API + '/seasons/' + id + '/close', '', auth.getAuthHeader()).then(response => {
          return axios.get(this.$config.API + 'seasons', auth.getAuthHeader())
        }).then(response => {
          self.seasons = response.data
        }).catch(e => {
          throw e
        })
      },
      showRapport: function (id) {
        this.$router.push({path: '/panel/seasons/' + id + '/rapport'})
      },
      isAdmin: function () {
        return auth.isAdmin()
      }
    }
  }
</script><!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

</style>
