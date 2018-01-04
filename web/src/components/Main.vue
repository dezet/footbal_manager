<template>
  <div class="mainWrapper">
    <h1 class="text-center">
      Witaj w Football Manager!
    </h1>
    <p>
      Dzisiaj jest {{ date }}
    </p>
  </div>
</template>

<script>
  function getNowDate () {
    var date = new Date()
    var str = ''

    if (date.getDate() < 10) str += ('0' + date.getDate())
    else str += date.getDate()

    str += '-' + date.getMonth() + '-' + date.getFullYear()

    return str
  }

  var data = {
    message: 'Siemka',
    date: getNowDate()
  }

  export default {
    name: 'Main',

    data: function () {
      return data
    }
  }
</script>
