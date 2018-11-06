<template>
  <div class="wrapper">
    <header>
      <div class="page-title-wrapper">
        <h1 class="page-title">Informacje o sezonach</h1>
      </div>
    </header>

    <div class="content" id="content">
      <season-list></season-list>
    </div>
  </div>
</template>

<script>
  import SeasonsList from '@/components/admin/season/SeasonsList'

  export default {
    name: 'seasons',
    components: {
      'season-list': SeasonsList
    }
  }
</script>

<style scoped>

</style>
