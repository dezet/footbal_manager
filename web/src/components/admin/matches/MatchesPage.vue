<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Zarządzanie meczami</h1>
          </div>
        </header>
        <div class="content" id="content">
          <matches-list></matches-list>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import PanelMenu from '@/components/admin/PanelMenu'
  import MatchesList from '@/components/admin/matches/MatchesList'

  export default {
    name: 'matches-page',
    components: {
      'panel-menu': PanelMenu,
      'matches-list': MatchesList,
    }
  }
</script>

<style scoped>

</style>
