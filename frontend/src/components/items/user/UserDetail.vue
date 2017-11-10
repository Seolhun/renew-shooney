<template>
  <div class="container">
    <div class="row margin-30">
      <div class="col-md-12 col-sm-12">
        <h2>User Detail</h2>
        <p>User ID : {{ $route.params.id }}</p>
        <p>User Nickname {{ $route.params.nickname }}</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        link: {
          name: 'UserDetail',
          params: {
            nickname: this.$route.params.nickname,
            idx: this.$route.params.id
          },
          query: {
            locale: 'en',
            q: 100
          },
          hash: '#data'
        }
      }
    },
    methods: {},
    computed: {},
    watch: {}
  }
</script>

<style lang="scss">

</style>
