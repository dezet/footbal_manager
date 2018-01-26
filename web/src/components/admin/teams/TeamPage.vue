<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Informacje o zespołach</h1>
          </div>
        </header>

        <div class="content" id="content">
          <team-list></team-list>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  import PanelMenu from '@/components/admin/PanelMenu'
  import TeamList from '@/components/admin/teams/TeamList'
  import axios from 'axios'
  import auth from '../../../authentication'

  export default {
    name: 'TeamPage',
    data: function () {
      return {
        season: {},
        timetable: []
      }
    },
    components: {
      'panel-menu': PanelMenu,
      'team-list': TeamList
    }
  }
</script>

<style scoped>

</style>
