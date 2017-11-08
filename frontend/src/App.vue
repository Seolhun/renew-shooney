<template>
  <div id="app">
    <header-menu>

    </header-menu>
    <div class="margin-top-30 margin-bottom-30">
      <bread-crumb>

      </bread-crumb>
    </div>

    <div class="margin-top-30 margin-bottom-30">
      <router-view>

      </router-view>
    </div>

    <!--
    <div class="container">
      <div class="el-row">
        <div class="el-col-sm-24">
          <button
            class="el-button el-button&#45;&#45;danger"
            @click="changeLang('ko')"
          >
            Korean
          </button>
          <button
            class="el-button el-button&#45;&#45;danger"
            @click="changeLang('en')"
          >
            English
          </button>
          <button
            class="el-button el-button&#45;&#45;danger"
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
    -->

    <footer-menu>

    </footer-menu>
  </div>
</template>

<style lang="sass">
  @import "assets/scss/app"

</style>


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
      },
      goBack () {
        this.router.go(-1)
      },
      destroy () {
        // Instance be destroyed.
        this.$destroy()
      }
    },
    // Open the browser console
    beforeCreate () {
      console.log('BeforeCreate In App Vue')
    },
    beforeMount () {
      console.log('BeforeMount In App Vue')
    },
    mounted () {
      console.log('Mounted In App Vue')
    },
    create () {
      console.log('Create In App Vue')
    },
    beforeUpdate () {
      console.log('BeforeUpdate In App Vue')
    },
    updated () {
      console.log('Updated In App Vue')
    },
    beforeDestroy () {
      console.log('BeforeDestroy In App Vue')
    },
    destroyed () {
      console.log('Destroyed In App Vue')
    }
  }
</script>
