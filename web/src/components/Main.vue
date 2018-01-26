<template>
  <div class="wrapper">
    <header>
      <section id="header">
        <div id="title">
          <h1>Witaj w Football Managerze!</h1>
        </div>
      </section>
      <main-menu/>
    </header>

    <section id="about-us"></section>

    <section id="fixed-1"></section>

    <section id="creators"></section>

    <section id="contact"></section>

    <footer>
      <section id="footer"></section>
    </footer>
  </div>
</template>

<script>
  import MainMenu from '@/components/MainMenu'

  export default {
    name: 'Main',
    components: {
      'main-menu': MainMenu
    },
    methods: {
      pageScroll () {
        window.scrollBy(0, 5)
      }
    }
  }
</script>
