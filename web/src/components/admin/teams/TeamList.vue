<template>
  <div class="teams">
    <button v-if="isAdmin()" class="btn btn-success" v-on:click="showModal = true"><i class="fa fa-pencil"
                                                                                      aria-hidden="true"></i>
      Dodaj zespół
    </button>
    <button class="btn btn-success" v-on:click="showAssignTeamModal = true"><i class="fa fa-pencil"
                                                                               aria-hidden="true"></i>
      Dołącz do zespołu
    </button>
    <modal v-if="showModal" :leagues="leagues" @closeModal="closeModal()" @addTeam="addTeam()"
           v-on:show="showModal = true"/>
    <assignToTeamModal v-if="showAssignTeamModal" :teams="teams" @closeModal="showAssignTeamModal = false" @assignTeam="assignTeam()"
                       v-on:show="showModal = true"/>
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
            <button v-if="isAdmin()" class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i> Edit
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
  import TeamModal from '@/components/admin/teams/TeamModal'
  import AssignToTeamModal from '@/components/admin/teams/AssignToTeamModal'

  export default {
    name: 'team-list',
    components: {
      'modal': TeamModal,
      'assignToTeamModal': AssignToTeamModal
    },
    // data used in template
    data: function () {
      return {
        showModal: false,
        showAssignTeamModal: false,
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
      closeModal: function () {
        this.showModal = false
      },
      addTeam: function () {
        this.closeModal()
        let self     = this
        this.newteam = {
          name: $('input#name').val(),
          leagueId: $('select#league').val()
        }
        axios.post(this.$config.API + 'teams', this.newteam, auth.getAuthHeader()).then(response => {
          self.newteam = {}
          return axios.get(this.$config.API + 'teams', auth.getAuthHeader())
        }).then(response => {
          self.teams = response.data
        }).catch(e => {
          throw e
        })
      },
      assignTeam: function () {
        this.showAssignTeamModal = false
        console.log();
        axios.get(this.$config.API + 'teams/' + $('#team').val() + '/assign', auth.getAuthHeader()).
              then(response => {
                return axios.get(this.$config.API + 'teams', auth.getAuthHeader())
              }).
              then(response => {
                self.teams = response.data
              }).
              catch(e => {
                throw e
              })
      },
      isAdmin: function () {
        return auth.isAdmin()
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
