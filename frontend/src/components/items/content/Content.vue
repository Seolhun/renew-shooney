<template>
  <div>
    <common-list
      :listType="listType"
      :results="results"
      :filters="filters"
    >
    </common-list>
  </div>
</template>

<style lang="scss">
  @import "../../../assets/scss/items/content/content";
</style>

<script>
  import DummyBlogs from '@/assets/dummy/content.json'
  import CommonList from '@/components/common/list/CommonList'

  export default {
    components: {
      CommonList
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
