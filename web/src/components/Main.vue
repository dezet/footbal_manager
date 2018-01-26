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

    <section id="about-us">
      <div class="container">
        <div class="text-block">
          <h3>Witaj w Footbal Managerze!</h3>

          <p>Wraz z sukcesami polskiej reprezentacji, wśród młodzieży rośnie potrzeba identyfikacji ze swoimi idolami. Każdy z nich chce być jak Lewandowski i chce poczuć jak to jest strzelać bramki tak jak on. </p>
          <p>Niestety w czasach powszechnego dostępu do internetu, coraz więcej osób woli spędzać czas przed komputerem niż poza domem. Dlatego takie inicjatywy jak amatorskie ligi piłkarskie są dla tych młodych ludzi wybawieniem. A ta aplikacja pomoże tym wszystkim sprawnie zarządzać!</p>
        </div>
      </div>
    </section>

    <section id="fixed-1"></section>

    <section id="creators">
      <div class="container">
        <div class="text-block">
          <h3>Autorzy</h3>

          <div class="authors-wrapper">
            <div class="author-card">
              <header>
                <img src="/static/image/profil.jpg" alt="">
              </header>
              <main>
                <h4>Dominik Dziąg</h4>
              </main>
            </div>

            <div class="author-card">
              <header>
                <img src="/static/image/profil.jpg" alt="">
              </header>
              <main>
                <h4>Michał Koszałka</h4>
              </main>
            </div>

            <div class="author-card">
              <header>
                <img src="/static/image/profil.jpg" alt="">
              </header>
              <main>
                <h4>Damian Kryger</h4>
              </main>
            </div>
          </div>
        </div>
      </div>
    </section>

    <footer>
      <section id="footer">
        <span class="copyright">&copy; QWERTY TEAM 2018</span>
      </section>
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

<style scoped>
  .text-block {
    width: 75%;
    margin: 0 auto;
  }

  .text-block h3 {
    font-size: 32px;
    font-weight: 900;
    text-align: center;
  }

  .text-block p {
    font-size: 24px;
  }

  section .container {
    display: flex;
    align-items: center;
    height: 100%;
  }

  .authors-wrapper {
    display: flex;
  }

  .author-card {
    margin: 20px;
  }

  .author-card img {
    width: 100%;
  }

  .author-card h4 {
    text-align: center;
  }

  .copyright {
    color: #ffffff;
    text-align: center;
    padding: 20px;
    display: block;
  }
</style>
