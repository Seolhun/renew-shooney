<template>
  <div class="container">
    <div class="row margin-30">
      <div class="col-md-12 col-sm-12">
        <h2>{{ msg }}</h2>
      </div>
    </div>
    <div class="row margin-30">
      <div class="col-md-12 col-sm-12">
        <ul>
          <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
          <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
          <li><a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a></li>
          <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
        </ul>
      </div>
    </div>
    <div class="row margin-30">
      <div class="col-md-12 col-sm-12">
        <div class="container unauthenticated">
          <div>
            With Facebook: <a href="/login/facebook">click here</a>
          </div>
          <div>
            With Github: <a href="/login/github">click here</a>
          </div>
        </div>
        <p>
          Code Value : {{ $route.query.code }}
        </p>
      </div>
    </div>
    <div>
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-sm-12">
            <!-- Vue i18n - Language -->
            <h1>Vue i18n</h1>
            <button
              class="el-button el-button--danger"
              @click="changeLang('ko')" w
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
          <div class="col-md-12 col-sm-12">
            <p>{{ $tc('nation', 1) }}</p>
            <p>{{ $tc('nation', 2) }}</p>
            <p>{{ $t('message') }}</p>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12 col-sm-12">
            <p>getSearchedList : {{ getSearchedList.items }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    data () {
      return {
        msg: 'Welcome to SeolHun Vue.js App'
      }
    },
    computed: mapGetters({
      getSearchedList: 'getSearchedList'
    })
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass">

</style>
