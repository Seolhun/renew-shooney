<template>
  <div class="container">
    <div class="row margin-30">
      <div class="col-md-12 col-sm-12">
        <h2>Blog Detail</h2>
        <p>Blog Idx {{ $route.params.idx }}</p>
        <p>Created By {{ $route.params.nickname }}</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        link: {
          name: 'BlogDetail',
          params: {
            nickname: this.$route.params.nickname,
            idx: this.$route.params.idx
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
