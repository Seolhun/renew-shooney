<template>
  <header id="header container row" class="header header-shadow-box">
    <div class="container">
      <div class="el-row">
        <div class="header-wrap el-col-sm-24">
          <a href="#" id="menu-open" class="menu-open header-btn i-menu hide-after-lg"></a>
          <div class="header-logo">
            <router-link :to="'/'" class="header-logo-link">
              <img src="../../assets/img/logo3.png" alt="Site Logo">
            </router-link>
          </div>

          <nav class="header-navigation">
            <!---->
            <ul class="header-navigation-wrap u-unstyled header-show">
              <li>
                <a class="navigation-link nav-toggle active" aria-hidden="false">

                </a>
              </li>
            </ul>
            <!-- Web -->
            <ul class="header-navigation-wrap u-unstyled header-hide">
              <router-link class="navigation-link" tag="li" :to="'notice'">Notice</router-link>
              <router-link class="navigation-link" tag="li" :to="'supporters'">Supporters</router-link>
              <router-link class="navigation-link" tag="li" :to="'blog'">Blog</router-link>
              <router-link class="navigation-link" tag="li" :to="'aboutme'">About Me</router-link>
            </ul>
          </nav>

          <a href="#" id="search-btn" class="search-btn header-btn hide-after-md i-close i-search"></a>

          <div class="header-social hide-before-lg">

          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
  export default {
    name: 'headerMenu'
  }
</script>

<style lang="sass">
  @import "../../assets/scss/layout/header"
</style>


