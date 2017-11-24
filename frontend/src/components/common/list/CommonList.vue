<template>
  <div class="container">
    <div class="row margin-top-30">
      <div class="col-sm-12 col-md-12 text-center">
        <div class="width-75 btn-group">
          <input
            :placeholder="$tc('contents.placeholder.search')"
            class="form-control margin-right-10"
            v-model.lazy.prevent="filters.text"/>
          <button class="btn btn-ocean">Search</button>
        </div>
        <router-link
          tag="button"
          :to="'/editor'"
          class="btn btn-limegreen"
        >
          Editor
        </router-link>
      </div>
    </div>

    <!-- Card -->
    <card-list
      :items="searchedItems"
    >

    </card-list>

    <b-pagination
      align="center"
      size="md"
      :limit="10"
      :total-rows="results.totalCount"
      :per-page="results.pageSize"
      v-model="results.pageIndex"
    >
    </b-pagination>
  </div>
</template>

<script>
  import CardList from '@/components/common/list/types/CardList'

  export default {
    // You can see this field values in Content.vue
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
    methods: {
      // To search Items without space
      removeSpace (str) {
        return str.replace(/\s/g, '')
      },

      // To Search Is Not Active
      isActiveItems (items) {
        let result = []
        items.forEach(function (item) {
          if (item.isActive) {
            result.push(item)
          }
        })
        return result
      },
      // To Search Function
      searchItems (items) {
        // To use This in forEach function
        let vm = this

        let filterText = vm.removeSpace(this.filters.text.trim().toLowerCase())
        if (filterText.length < 1) {
          this.results.totalCount = items.length
          return items
        }

        let activeItems = vm.isActiveItems(items)
        let searchedList = []
        let bool, bool2, bool3
        activeItems.forEach(function (item) {
          if (item.isActive) {
            bool = vm.removeSpace(item.title.toLowerCase()).indexOf(filterText) > -1
            bool2 = vm.removeSpace(item.blogContent.toLowerCase()).indexOf(filterText) > -1
            bool3 = vm.removeSpace(item.createdBy.toLowerCase()).indexOf(filterText) > -1
            if (bool || bool2 || bool3) {
              searchedList.push(item)
            }
          }
        })
        this.results.totalCount = searchedList.length

        // Set Vuex
        this.$store.dispatch('setSearchedList', this.results)
        return searchedList
      }
    },
    computed: {
      searchedItems () {
        // Local Variable
        let startNum, lastNum, pageIndex
        let items = this.results.items
        let counts = 0
        // Remove Not isActive
        items = this.searchItems(items)

        // Paging Logic
        pageIndex = this.results.pageIndex
        startNum = (pageIndex <= 1 ? 1 : (pageIndex - 1) * this.results.pageSize + 1)
        lastNum = (pageIndex * this.results.pageSize >= this.results.totalCount ? this.results.totalCount : pageIndex * this.results.pageSize)

        return items.filter((item, index) => {
          if (counts <= this.results.pageSize) {
            let itemIndex = (index + 1)
            if (startNum <= itemIndex && lastNum >= itemIndex) {
              counts++
              return item
            }
          }
        })
      }
    }
  }
</script>

<style lang="scss">

</style>
