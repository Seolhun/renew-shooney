<template>
  <div class="container">
    <div class="row margin-top-30">
      <router-link
        class="col-sm-12 col-sm-12 col-md-6 col-lg-6 card-item max-height-800 router-item"
        v-for="item in items"
        :key="item.id"
        :to="'/content/' + item.baseCreatedBy.createdByNickname +'/'+ item.idx"
        @click.stop="onclick()"
      >
        <div
          class="card-group"
          v-if="item.isActive"
        >
          <div class="text-center">
            <img
              src="~assets/img/logo/logo2.png"
              class="card-img padding-10 max-height-300"
            >
          </div>
          <div class="padding-20">
            <span class="label-title-24">
              {{ item.title }}
            </span>
            <div class="bottom clearfix text-right">
              <div class="content-else">
                Created By
                <a
                  class="router-item content-created-by"
                  :href="'https://github.com/' + item.baseCreatedBy.createdByNickname"
                  target="_blank">
                  {{ item.baseCreatedBy.createdByNickname }}
                </a>
              </div>
              <div class="content-else">
                <time class="time">{{ item.createdDate }}</time>
              </div>
            </div>
          </div>
        </div>
      </router-link>
    </div>
    <div class="row margin-top-30">

    </div>
  </div>
</template>

<script>
  export default {
    props: ['items'],
    data () {
      return {
        itemMargin: {
          'margin': 5 + 'px'
        }
      }
    },
    methods: {},
    computed: {}
  }
</script>

<style lang="scss">
  @import "../../../../assets/scss/common/list/card/cardList";
</style>
