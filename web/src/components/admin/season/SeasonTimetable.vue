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

        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import PanelMenu from '@/components/admin/PanelMenu'

  export default {
    name: 'Panel',
    components: {
      'panel-menu': PanelMenu
    }
  }
</script>

<style scoped>

</style>
