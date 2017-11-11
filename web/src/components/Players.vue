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
    axios.post(`http://localhost:8124/players`)
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
