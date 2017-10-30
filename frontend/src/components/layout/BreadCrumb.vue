<template>
  <div class="container" id="markdown">
    <div class="el-row">
      <div class="el-col-md-24">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item
            :to="{ path: '/' }"
          >
            fdas
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        paths: []
      }
    },
    computed: {
      init () {
        this.paths = this.props
      }
    }
  }
</script>

<style lang="scss">

</style>
