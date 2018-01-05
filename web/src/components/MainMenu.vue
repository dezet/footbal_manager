<template>
  <nav id="mainMenu" class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menuCollapse" aria-expanded="false">
          <span class="sr-only">Rozwiń menu</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Football manager</a>
      </div>

      <div class="collapse navbar-collapse" id="menuCollapse">
        <ul class="nav navbar-nav">
          <li>
            <a href="#">Test 1</a>
          </li>
          <li>
            <a href="#">Test 2</a>
          </li>
          <li>
            <a href="#">Test 3</a>
          </li>
        </ul>
      </div>
    </div>

  </nav>
</template>

<script>
  export default {
    name: 'main-menu'
  }
</script>

<style scoped>
  .navbar {
    margin-bottom: 0;
  }
</style>
