<template>
  <div class="wrapper">
    <header>
      <div class="page-title-wrapper">
        <h1 class="page-title">Informacje o ligach</h1>
      </div>
    </header>

    <div class="content" id="content">
      <league-list></league-list>
    </div>
  </div>
</template>

<script>

  import LeagueList from '@/components/admin/leagues/LeagueList'

  export default {
    name: 'leagues',
    components: {
      'league-list': LeagueList
    }
  }
</script>

<style scoped>

</style>
