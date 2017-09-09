<template>
  <div>
    <b-jumbotron class="gradient-fc" header="Blog" lead="Bootstrap 4 Components for Vue.js 2">
      <div class="row margin-20">
        <div class="col-sm-12">
          <div class="text-right">
            <button class="fc-btn">Write</button>
          </div>
        </div>
      </div>
    </b-jumbotron>

    <div class="container">
      <div class="row margin-20">
        <div class="col-sm-12">
          <blog-table>

          </blog-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import BlogTable from './BlogTable.vue'

  export default {
    components: {BlogTable},
    data () {
      return {
        watch: {

        }
      }
    }
  }
</script>

<style lang="scss">

</style>

