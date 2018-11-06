<template>
  <div class="wrapper">
    <header>
      <div class="page-title-wrapper">
        <h1 class="page-title">Informacje o meczach</h1>
      </div>
    </header>
    <div class="content" id="content">
      <matches-list></matches-list>
    </div>
  </div>
</template>

<script>
  import MatchesList from '@/components/admin/matches/MatchesList'

  export default {
    name: 'matches',
    components: {
      'matches-list': MatchesList
    }
  }
</script>

<style scoped>

</style>
