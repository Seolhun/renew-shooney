<template>
  <div id="app">
    <header-menu>

    </header-menu>
    <div class="margin-top-30 margin-bottom-30">
      <bread-crumb>

      </bread-crumb>
    </div>

    <div class="margin-top-30 margin-bottom-30">
      <router-view></router-view>
    </div>

    <div class="container">
      <div class="el-row">
        <div class="el-col-sm-24">
          <button
            class="el-button el-button--danger"
            @click="changeLang('ko')"
          >
            Korean
          </button>
          <button
            class="el-button el-button--danger"
            @click="changeLang('en')"
          >
            English
          </button>
          <button
            class="el-button el-button--danger"
            @click="changeLang('ja')"
          >
            Japan
          </button>
        </div>
        <div>
          <p>{{ $tc('nation', 1) }}</p>
          <p>{{ $tc('nation', 2) }}</p>
          <p>{{ $t('message') }}</p>
        </div>
      </div>
    </div>

    <footer-menu>

    </footer-menu>
  </div>
</template>

<script>
  import HeaderMenu from './components/layout/Header.vue'
  import FooterMenu from './components/layout/Footer.vue'
  import BreadCrumb from './components/layout/BreadCrumb.vue'
  export default {
    components: {
      HeaderMenu,
      FooterMenu,
      BreadCrumb
    },
    name: 'app',
    data () {
      return {}
    },
    methods: {
      changeLang (langs) {
        this.$i18n.locale = langs
      }
    },
    computed: ''
  }
</script>

<style lang="sass">
  @import "assets/scss/app"

</style>
