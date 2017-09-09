<template>
  <footer class="footer">
    <div class="container padding-30">
      <div class="row text-center">
        <div class="col-sm-6 col-xs-12 footer-header">
          <h4>Company</h4>
          <ul class="footer-header-a">
            <li><a href="/company/about/">About</a></li>
            <li><a href="/company/blog/">Blog</a></li>
            <li><a href="/legal/terms/">Legal</a></li>
          </ul>
        </div>
        <div class="col-sm-6 col-xs-12 footer-header">
          <h4>Products</h4>
          <ul class="footer-header-a">
            <li class="footer-header-a"><a href="/products/compute/">Compute</a></li>
            <li><a href="/products/storage/">Storage</a></li>
            <li><a href="/products/teams/">Teams</a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
  export default {
    name: 'footer-menu'
  }
</script>

<style lang="sass">
  @import "../../assets/scss/layout/footer"
</style>


