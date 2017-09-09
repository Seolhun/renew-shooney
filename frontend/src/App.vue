<template>
  <!-- built files will be auto injected -->
  <div id="app">
    <header-menu>

    </header-menu>
    <router-view>

    </router-view>
    <footer-menu>

    </footer-menu>
  </div>
</template>

<script>
  import HeaderMenu from './components/layout/Header.vue'
  import FooterMenu from './components/layout/Footer.vue'

  export default {
    components: {HeaderMenu, FooterMenu},
    name: 'app'
  }
</script>

<style lang="sass">
  @import "assets/scss/app"

</style>
