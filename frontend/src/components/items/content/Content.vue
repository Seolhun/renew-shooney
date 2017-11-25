<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="text-right">
            <label class="form-inline">리스트 타입을 고르세요.
              <select class="form-control">
                <option
                  v-for="type in listTypes"
                >
                  {{ type.value }}
                </option>
              </select>
            </label>
          </div>
        </div>
      </div>
    </div>
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
        listTypes: [
          {
            id: 1,
            value: 'Card'
          },
          {
            id: 2,
            value: 'Row'
          }
        ],
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
      changeListType (listType) {
        this.listType = listType
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
