<template>
  <div>
    <div class="el-row margin-top-30 text-center">
      <div class="el-col-sm-12 btn-group">
        <input
          class="el-input margin-right-10"
          v-model.lazy.prevent="filters.text"/>
        <button class="el-button el-button--primary">Search</button>
      </div>
    </div>

    <div class="el-row margin-top-30">
      <div class="el-col-sm-24">
        <!-- Card -->
        <card-list
          :items="filterResults"
        >

        </card-list>
      </div>
    </div>

    <div class="el-row margin-top-30 text-center">
      <div class="el-col-xs-24 btn-group">
        <div class="block">
          <el-pagination
            layout="prev, pager, next"
            :current-page.sync="results.pageIndex"
            :total="results.totalCount"
            :page-size="results.pageSize"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import CardList from '@/components/common/list/types/CardList'
  export default {
    props: {
      listType: {
        type: Number,
        required: true
      },
      results: {
        type: Object,
        required: true
      },
      filters: {
        type: Object,
        required: true
      }
    },
    components: {
      CardList
    },
    data () {
      return {
      }
    },
    compute: {
      created () {
        console.log('Created mixin in MainList.vue')
      }
    },
    methods: {
      removeSpace (value) {
        return value.replace(/\s/g, '')
      },
      // To Search Is Not Active
      extractIsNotActiveItems (items) {
        // console.log('Before', items.length)
        items.forEach(function (item, index) {
          if (!(item.isActive)) {
            items.splice(index, 1)
          }
        })
        // console.log('After', items.length)
        this.results.totalCount = items.length
        return items
      },
      // To Search Function
      extractFilterItems (items) {
        let filterText = this.filters.text.trim()
        if (filterText === '') {
          return items
        }
        let result = []
        let vm = this
        items.forEach(function (item, index) {
          if (item.isActive) {
            let bool, bool2, bool3
            bool = vm.removeSpace(item.title.toLowerCase()).indexOf(vm.removeSpace(filterText.toLowerCase())) > -1
            bool2 = vm.removeSpace(item.content.toLowerCase()).indexOf(vm.removeSpace(filterText.toLowerCase())) > -1
            bool3 = vm.removeSpace(item.createdBy.toLowerCase()).indexOf(vm.removeSpace(filterText.toLowerCase())) > -1
            if (bool || bool2 || bool3) {
              result.push(item)
            }
          }
        })
        this.results.totalCount = result.length
        return result
      }
    },
    computed: {
      filterResults () {
        // Local Variable
        let startNum, lastNum, pageIndex
        let items = this.results.items
        let filters = this.filters
        let counts = 0
        // Remove Not isActive
        items = this.extractIsNotActiveItems(items)
        if (filters.text.trim() !== '') {
          items = this.extractFilterItems(items)
        }
        // Paging Logic
        pageIndex = this.results.pageIndex
        startNum = (pageIndex <= 1 ? 1 : (pageIndex - 1) * this.results.pageSize + 1)
        lastNum = (pageIndex * this.results.pageSize >= this.results.totalCount ? this.results.totalCount : pageIndex * this.results.pageSize)
        // this.results.items = items
        // this.filters = filters
        return items.filter((item, index) => {
          if (item.isActive && counts <= this.results.pageSize) {
            let itemIndex = (index + 1)
            if (startNum <= itemIndex && lastNum >= itemIndex) {
              counts++
              // console.log('else', itemIndex)
              return item
            }
          }
        })
      }
    },
    watch: {

    }
  }
</script>

<style lang="scss">

</style>
