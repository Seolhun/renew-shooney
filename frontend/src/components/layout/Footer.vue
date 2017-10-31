<template>
  <footer class="footer">
    <div class="container padding-30">
      <div class="row text-center">
        <div class="col-sm-6 col-xs-12 footer-header">
          <h4>Company</h4>
          <ul class="footer-header-a">
            <li>
              <router-link :to="'notice'" class="navigation-link">Notice</router-link>
            </li>
            <li>
              <router-link :to="'supporters'" class="navigation-link">Supporters</router-link>
            </li>
            <li>
              <router-link :to="'blog'" class="navigation-link">Blog</router-link>
            </li>
            <li>
              <router-link :to="'aboutme'" class="navigation-link">About Me</router-link>
            </li>
          </ul>
        </div>
        <div class="col-sm-6 col-xs-12 footer-header">
          <h4>Products</h4>
          <ul class="footer-header-a">
            <li>
              <router-link :to="'notice'" class="navigation-link">Notice</router-link>
            </li>
            <li>
              <router-link :to="'supporters'" class="navigation-link">Supporters</router-link>
            </li>
            <li>
              <router-link :to="'blog'" class="navigation-link">Blog</router-link>
            </li>
            <li>
              <router-link :to="'aboutme'" class="navigation-link">About Me</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
  export default {
    name: 'footerMenu'
  }
</script>

<style lang="sass">
  @import "../../assets/scss/layout/footer"
</style>


