<template>
  <div class="season-list">
    <h1>Sezony</h1>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody name="table-row">
      <tr v-for="season in seasons" :key="season.id">
        <td>{{season.name}}</td>
        <td>{{season.year}}</td>
        <td>
          <button v-on:click="generateTimetable(season)">Generate timetable</button>
        </td>
      </tr>
      </tbody>
    </table>

  </div>

</template>

<script>
  import http from '../util/http'
  export default {
    name: 'GenerateTimetable',
    data: function () {
      return {
        seasons: []
      }
    },
    created () {
      http().get(this.$config.API + 'seasons').then(response => {
        this.seasons = response.data
      }).catch(e => {
        console.log(e)
      })
    },
    methods: {
      generateTimetable: function (season) {
        http().get(this.$config.API + 'seasons/' + season.id + '/generate')
      }
    }
  }
</script>
