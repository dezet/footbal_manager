<template>
  <div class="wrapper">
    <div class="row">
      <div class="panel-sidebar">
        <panel-menu></panel-menu>
      </div>
      <div class="panel-main">
        <header>
          <div class="page-title-wrapper">
            <h1 class="page-title">Zarządzanie sezonami</h1>
          </div>
        </header>

        <div class="content" id="content">
          <season-list></season-list>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import PanelMenu from '@/components/admin/PanelMenu'
  import SeasonsList from '@/components/admin/season/SeasonsList'

  export default {
    name: 'season-page',
    components: {
      'panel-menu': PanelMenu,
      'season-list': SeasonsList,
    }
  }
</script>

<style scoped>

</style>
