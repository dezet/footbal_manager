<template>
  <div id="app">
    <header>
      <main-menu/>
    </header>

    <div class="wrapper">
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
#app {
}
</style>
