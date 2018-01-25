<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Informacje o ligach</h1>
          </div>
        </header>

        <div class="content" id="content">
          <league-list></league-list>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  import PanelMenu from '@/components/admin/PanelMenu'
  import LeagueList from '@/components/admin/leagues/LeagueList'
  import axios from 'axios'
  import auth from '../../../authentication'

  export default {
    name: 'LeaguePage',
    data: function () {
      return {
        season: {},
        timetable: []
      }
    },
    components: {
      'panel-menu': PanelMenu,
      'league-list': LeagueList
    }
  }
</script>

<style scoped>

</style>
