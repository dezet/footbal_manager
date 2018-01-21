<template>
  <nav>
    <header>
      <div class="ball">
        <i class="fa fa-futbol-o fa-5x" aria-hidden="true"></i>
      </div>
      <h3>Witaj!</h3>
    </header>
    <main>
      <ul>
        <li>
          <a href="/panel">Sezony</a>
        </li>
        <li>
          <a href="/panel">Ligi</a>
        </li>
        <li>
          <a href="/panel">Zespoły</a>
        </li>
        <li>
          <a href="/panel">Mecze</a>
        </li>
        <hr>
        <li>
          <button class="button-unstyled" v-on:click="onLogoutClicked">Wyloguj
          </button>
        </li>
      </ul>
    </main>

    <footer>

    </footer>
  </nav>
</template>

<script>
  import auth from '../../authentication'
  import router from '../../router'

  export default {
    name: 'panel-menu',
    methods: {
      onLogoutClicked () {
        auth.logout()
        router.push('/')
      }
    }
  }
</script>

<style scoped>

  .button-unstyled {
    border: none;
    background: transparent;
  }

</style>
