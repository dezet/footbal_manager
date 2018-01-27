<template>
  <div class="matches">
    <div class="matches_list">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Home</th>
          <th>Away</th>
          <th></th>
        </tr>
        </thead>
        <tbody name="table-row">
        <tr v-for="match in matches" :key="match.id" v-on:click="showMatch(match.id)">
          <td :key="match.id">
          <td>{{match.home.name}}</td>
          <td>{{match.away.name}}</td>
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

  export default {
    name: 'match-list',
    // data used in template
    data: function () {
      return {
        matches: []
      }
    },
    // initial data load
    created: function () {
      let self = this
      axios.get(this.$config.API + 'matches', auth.getAuthHeader()).then(response => {
        self.matches = response.data
        console.log(response.data)
      }).catch(e => {
        throw e
      })
    },
    methods: {
      showMatch: function(id){
        this.$router.push('/panel/matches/'+id);
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
