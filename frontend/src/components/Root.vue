<template>
  <div class="main">
    <b-jumbotron
      class="gradient-fc"
      header="Welcome to Shooney's blog"
      lead="Spring boot, Vuejs2">

      <div class="row margin-20">
        <div class="col-sm-12">

        </div>
      </div>
    </b-jumbotron>

    <div class="height-300">
      <div class="container">
        <div class="row margin-30">
          <div class="col-sm-12">
            <h4>{{ hello }}</h4>
            <ul>
              <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
              <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
              <li><a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a></li>
              <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        hello: 'Welcome to Hun, Seol Blog'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass">

</style>
