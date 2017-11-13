<template>
  <header id="header container row" class="header header-shadow-box">
    <div class="container">
      <div class="row">
        <div class="header-wrap col-sm-12">
          <div class="header-logo">
            <router-link :to="'/'" class="header-logo-link">
              <img src="../../assets/img/logo3.png" alt="Site Logo">
            </router-link>
          </div>

          <nav class="header-navigation">
            <!-- Web -->
            <ul class="header-link-unstyle header-lg">
              <router-link class="navigation-link" tag="li" :to="'/blog'">Blog</router-link>
            </ul>

            <ul class="header-link-unstyle header-xs">
              <li>
                <a class="navigation-link nav-toggle active" aria-hidden="false">

                </a>
              </li>
            </ul>
          </nav>

          <div class="header-search-div btn-group">
              <span class="">
                <svg class="svg-search" width="25" height="25" viewBox="0 0 25 25">
                  <path d="M20.067 18.933l-4.157-4.157a6 6 0 1 0-.884.884l4.157 4.157a.624.624 0 1 0 .884-.884zM6.5 11c0-2.62 2.13-4.75 4.75-4.75S16 8.38 16 11s-2.13 4.75-4.75 4.75S6.5 13.62 6.5 11z"></path>
                </svg>
              </span>
            <input class="form-control" type="search" placeholder="Search Hi-Cord" required="true">
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="sass">
  @import "../../assets/scss/layout/header"
</style>

<script>
  export default {
    name: 'headerMenu'
  }
</script>




