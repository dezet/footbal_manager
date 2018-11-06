<template>
  <div class="wrapper">
    <header>
      <div class="page-title-wrapper">
        <h1 class="page-title">Informacje o zespołach</h1>
      </div>
    </header>

    <div class="content" id="content">
      <team-list></team-list>
    </div>

  </div>
</template>

<script>

  import TeamList from '@/components/admin/teams/TeamList'

  export default {
    name: 'teams',
    components: {
      'team-list': TeamList
    }
  }
</script>

<style scoped>

</style>
