<template>
  <div>
    <div class="container">
      <div class="row margin-20">
        <div class="col-sm-12">
          <blog-list>

          </blog-list>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import BlogTable from './BlogTable.vue'
  import BlogList from './BlogList.vue'

  export default {
    props: {

    },
    components: {
      BlogTable,
      BlogList

    },
    data () {
      return {
        methods: {

        },
        computed: {

        },
        watch: {

        }
      }
    }
  }
</script>

<style lang="scss">

</style>

