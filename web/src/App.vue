<template>
  <div id="app">
    <header>
      <main-menu/>
    </header>

    <div class="main">
      <router-view/>
    </div>
  </div>
</template>

<script>
  import MainMenu from '@/components/MainMenu'

  export default {
    name: 'app',
    components: {
      'main-menu': MainMenu
    }
  }
</script>

<style>
  .main {
    flex: 1;
    width: 100%;
    height: 100%;
    min-height: 360px;
    display: flex;
  }
</style>
