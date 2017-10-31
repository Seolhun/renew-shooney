<template>
  <div class="container">
    <div class="row margin-30">
      <div class="col-sm-12">
        <h2>{{ msg }}</h2>
      </div>
    </div>
    <div class="row margin-30">
      <div class="col-sm-12">
        <ul>
          <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
          <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
          <li><a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a></li>
          <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        msg: 'Welcome to SeolHun Vue.js App'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass">

</style>
