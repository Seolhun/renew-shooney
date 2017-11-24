<template>
  <div>
    <div class="row">
      <div>
        <b-img src="https://cdn-images-1.medium.com/max/2000/1*gs2hvSZS09vGYwds-teNqw.jpeg" fluid
               alt="Responsive image"/>
      </div>
    </div>
    <div class="container">
      <div class="row margin-30">
        <div class="col-md-12 col-sm-12">
          <h2>Blog Detail</h2>
          <p>Blog Idx {{ $route.params.idx }}</p>
          <p>Created By {{ $route.params.nickname }}</p>
        </div>
      </div>
      <div class="row margin-30">
        <div class="col-md-12 col-sm-12">
          <p>{{ getContentById }}</p>
        </div>
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
    computed: {
      getContentById () {
        let idx = this.$route.params.idx
        let nickname = this.$route.params.nickname
        return {'idx': idx, 'nickname': nickname}
      }
    },
    watch: {}
  }
</script>

<style lang="scss">

</style>
