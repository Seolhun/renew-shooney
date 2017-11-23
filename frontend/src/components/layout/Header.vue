<template>
  <b-navbar
    class="background-ocean shadow-box col-sm-12"
    toggleable="md"
    type="dark"
    fixed="top"
  >
    <b-navbar-toggle target="nav_collapse">

    </b-navbar-toggle>

    <b-navbar-brand
      href="/"
    >
      Hi-Cord
    </b-navbar-brand>
    <b-collapse is-nav id="nav_collapse">
      <!-- Route Link -->
      <b-navbar-nav>
        <b-nav-item href="#">
          <router-link tag="li" :to="'/blog'">Blog</router-link>
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <!-- Search Function -->
        <b-nav-form>
          <div
            class="form-inline"
          >
            <b-form-input size="md" class="mr-sm-2" type="text" placeholder="Search">

            </b-form-input>
            <button
              size="md"
              class="btn-gray"
              type="button"
            >
              Search
            </button>
          </div>
        </b-nav-form>

        <!-- Right aligned nav items -->
        <b-nav-item-dropdown
          text="Lang"
          right
        >
          <b-dropdown-item href="#">EN</b-dropdown-item>
          <b-dropdown-item href="#">ES</b-dropdown-item>
          <b-dropdown-item href="#">RU</b-dropdown-item>
          <b-dropdown-item href="#">FA</b-dropdown-item>
        </b-nav-item-dropdown>

        <b-nav-item-dropdown
          class="header-navigation-link"
          right
        >
          <!-- Using button-content slot -->
          <template slot="button-content">
            <em>User</em>
          </template>
          <b-dropdown-item href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Signout</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>
</template>

<style lang="sass">
  @import "../../assets/scss/layout/header"
</style>

<script>
  export default {
    name: 'headerMenu'
  }
</script>




