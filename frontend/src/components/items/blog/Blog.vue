<template>
  <div class="container">
    <div class="el-row margin-top-30">
      <div class="el-col-md-24 text-right">
        <router-link :to="'editor'" class="navigation-link">
          <el-button type="primary">Editor</el-button>
        </router-link>
      </div>
    </div>

    <div class="el-row margin-top-30">
      <main-list
        :listType="listType"
        :results="results"
        :filters="filters"
      >
      </main-list>
    </div>
  </div>
</template>

<script>
  import DummyBlogs from '@/assets/dummy/blog.json'
  import MainList from '@/components/common/list/MainList'

  export default {
    components: {
      MainList
    },
    data () {
      return {
        listType: 1,
        // items is Array
        results: {
          items: DummyBlogs,
          pageIndex: 1,
          pageSize: 12,
          totalCount: DummyBlogs.length
        },
        filters: {
          type: 1,
          text: '',
          sortBy: 'desc'
        }
      }
    },
    methods: {
      // mouted Set Results
      mounted () {
        console.log('Mounted')
        // axios.get("/fa").then(response => this.results = response.data);
      }
    },
    watch: {
      filters (value) {
        console.log('filters', value)
      },
      pageIndex (value) {
        console.log('watch pageIndex', value)
      },
      pageSize (value) {
        console.log('watch pageSize', value)
      }
    }
  }
</script>

<style lang="scss">
  @import "../../../assets/scss/items/blog/blog";

</style>

