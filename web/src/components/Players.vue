<template>
  <div class="players">
    <h1>Gracze</h1>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Players',
  created () {
    axios.get(this.$config.API + 'players')
      .then(response => {
        // JSON responses are automatically parsed.
        console.log(response.data)
      })
      .catch(e => {
        console.log(e)
      })
  }
}
</script>
