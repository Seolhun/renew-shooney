<template>
  <div>
    <div class="row">
      <div class="col-sm-12">
        <h2></h2>
      </div>
    </div>

    <!-- Selecting per Paging -->
    <div class="row">
      <div class="col-sm-4">
        <b-form-fieldset
          horizontal
          label="Rows per page"
          :label-cols="6">
          <b-form-select
            :options="pageOptions"
            v-model="perPage">
          </b-form-select>
        </b-form-fieldset>
      </div>
    </div>

    <div class="row">
      <div class="col-sm-4 height-300 text-center" v-for="i in 10">
        <div>
          <img src="../../assets/img/logo.png"/>
        </div>
        <div class="text-left">
          <h5>Example heading <b-badge pill variant="primary">New</b-badge></h5>
        </div>
        <div>

        </div>
      </div>
    </div>

    <!-- Selecting No -->
    <div class="row">
      <div class="col-sm-12 text-center">
        <b-pagination
          align="center"
          size="sm"
          :total-rows="totalRows"
          :per-page="perPage"
          v-model="currentPage">
        </b-pagination>
        <p>
          Sort By: {{ sortBy || 'n/a' }}, Direction: {{ sortDesc ? 'descending' : 'ascending' }}
        </p>
      </div>
    </div>

    <!-- Details modal -->
    <b-modal id="modal1" @hide="resetModal" ok-only>
      <h4 class="my-1 py-1" slot="modal-header">Index: {{ modalDetails.index }}</h4>
      <pre>{{ modalDetails.data }}</pre>
    </b-modal>
  </div>
</template>

<script>
  const items = [
    {isActive: true, age: 40, name: {first: 'Dickerson', last: 'Macdonald'}},
    {isActive: false, age: 21, name: {first: 'Larsen', last: 'Shaw'}},
    {
      isActive: false, age: 9, name: {first: 'Mini', last: 'Navarro'}, _rowVariant: 'success'
    },
    {isActive: false, age: 89, name: {first: 'Geneva', last: 'Wilson'}},
    {isActive: true, age: 38, name: {first: 'Jami', last: 'Carney'}},
    {isActive: false, age: 27, name: {first: 'Essie', last: 'Dunlap'}},
    {isActive: true, age: 40, name: {first: 'Thor', last: 'Macdonald'}},
    {
      _cellVariants: {age: 'danger', isActive: 'warning'},
      isActive: true,
      age: 87,
      name: {first: 'Larsen', last: 'Shaw'}
    },
    {isActive: false, age: 26, name: {first: 'Mitzi', last: 'Navarro'}},
    {isActive: false, age: 22, name: {first: 'Genevive', last: 'Wilson'}},
    {isActive: true, age: 38, name: {first: 'John', last: 'Carney'}},
    {isActive: false, age: 29, name: {first: 'Dick', last: 'Dunlap'}}
  ]

  export default {
    name: 'blog-table',
    data () {
      return {
        items: items,
        fields: {
          title: {label: 'Person Full name', sortable: true},
          age: {label: 'Person age', sortable: true, 'class': 'text-center'},
          isActive: {label: 'is Active'},
          actions: {label: 'Actions'}
        },

        currentPage: 1,
        perPage: 10,
        totalRows: items.length,
        pageOptions: [{text: 5, value: 5}, {text: 10, value: 10}, {text: 15, value: 15}],
        sortBy: null,
        sortDesc: false,
        filter: null,
        modalDetails: {index: '', data: ''}
      }
    },
    methods: {
      details (item, index, button) {
        this.modalDetails.data = JSON.stringify(item, null, 2)
        this.modalDetails.index = index
        this.$root.$emit('show::modal', 'modal1', button)
      },
      resetModal () {
        this.modalDetails.data = ''
        this.modalDetails.index = ''
      },
      onFiltered (filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      }
    },
    computed: {

    },
    watch: {

    }
  }
</script>

<style lang="scss">

</style>

