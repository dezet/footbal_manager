<template>
  <nav id="main-menu" class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menuCollapse" aria-expanded="false">
          <span class="sr-only">Rozwiń menu</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#header">Football manager</a>
      </div>

      <div class="collapse navbar-collapse" id="menuCollapse">
        <ul class="nav navbar-nav">
          <li>
            <a href="#about-us">O nas</a>
          </li>
          <li>
            <a href="#creators">Twórcy</a>
          </li>
          <li>
            <a href="#contact">Kontakt</a>
          </li>
        </ul>

        <button class="btn btn-success navbar-btn navbar-right nav-btn" v-on:click="onLoginClicked">Logowanie</button>
        <button class="btn btn-primary navbar-btn navbar-right nav-btn" v-on:click="onSingUpClicked">Rejestracja
        </button>
      </div>
    </div>

  </nav>
</template>

<script>
  export default {
    name: 'main-menu',
    navbar: null,
    sticky: null,
    methods: {
      handleSticky () {
        if (window.pageYOffset >= this.sticky) {
          this.navbar.classList.add('sticky')
        } else {
          this.navbar.classList.remove('sticky')
        }
      },
      setSticky () {
        console.log(this.navbar.offsetTop)
        this.sticky = this.navbar.offsetTop
      },
      onLoginClicked () {
        this.$router.push({path: '/login'})
      },
      onSingUpClicked () {
        this.$router.push({path: '/signup'})
      }
    },
    created () {
      window.addEventListener('resize', this.setSticky)
      window.addEventListener('scroll', this.handleSticky)
    },
    mounted () {
      this.navbar = document.getElementById('main-menu')
      this.sticky = this.navbar.offsetTop
    },
    destroyed () {
      window.removeEventListener('scroll', this.handleSticky)
    }
  }
</script>

<style scoped>
  .navbar {
    margin-bottom: 0;
  }


</style>
