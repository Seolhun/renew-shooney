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
      tag="route-link"
      :to="'/'"
    >
      Hi-Cord
    </b-navbar-brand>
    <b-collapse is-nav id="nav_collapse">
      <!-- Route Link -->
      <b-navbar-nav>
        <b-nav-item href="#">
          <router-link tag="li" :to="'/content'">Contents</router-link>
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
          <b-dropdown-item @click="changeLang('ko')">한국어</b-dropdown-item>
          <b-dropdown-item @click="changeLang('en')">English</b-dropdown-item>
          <b-dropdown-item @click="changeLang('ja')">日本語</b-dropdown-item>
          <b-dropdown-item @click="changeLang('ch')">中文</b-dropdown-item>
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
          <b-dropdown-item href="#">
            <router-link tag="li" :to="'/'">Sign-In</router-link>
          </b-dropdown-item>
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
    name: 'headerMenu',
    methods: {
      // Change i18n Lang config
      changeLang (langs) {
        this.$i18n.locale = langs
      },
    }
  }
</script>




