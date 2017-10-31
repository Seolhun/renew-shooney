<template>
  <div>
    <el-carousel :interval="4000" type="card" height="400px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3>{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
  export default {
    data () {
      return {}
    },
    methods: {},
    computed: {},
    watch: {}
  }
</script>

<style lang="scss">
  @import "../../../assets/scss/items/supporter/supporter";
</style>
