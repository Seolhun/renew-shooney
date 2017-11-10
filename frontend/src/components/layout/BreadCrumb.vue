<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <b-breadcrumb
          :items="items">
        </b-breadcrumb>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        items: [{
          text: 'Admin',
          href: '#'
        }, {
          text: 'Manage',
          href: '#'
        }, {
          text: 'Library',
          active: true
        }]
      }
    }
  }
</script>

<style lang="scss">

</style>
