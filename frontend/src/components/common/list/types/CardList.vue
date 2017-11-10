<template>
  <div class="container">
    <div class="row margin-top-30">
      <b-col
        xs="12" sm="12" md="6" lg="6"
        v-for="item in items"
        :key="item.id"
      >
        <router-link
          tag="div"
          v-if="item.isActive"
          class="card-item margin-top-10 margin-bottom-10 max-height-700"
          :to="'/blog/' + item.createdBy +'/'+ item.idx"
          style="cursor: pointer"
        >
          <div class="card-group">
            <div class="text-center">
              <img
                src="~assets/img/logo2.png"
                class="card-img padding-10 max-height-300"
              >
            </div>
            <div class="padding-20">
            <span class="blog-title">
              {{ item.title }}
            </span>
              <div class="bottom clearfix">
                <div class="blog-created-by">
                  Created By
                  <a :href="'https://github.com/' + item.createdBy" target="_blank">
                    {{ item.createdBy }}
                  </a>
                  at
                  <time class="time">{{ item.createdDate }}</time>
                </div>
              </div>
            </div>
          </div>
        </router-link>
      </b-col>
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
    computed: {},
    watch: {
      pageIndex: (value) => {
        console.log('CardList', value)
      }
    }
  }
</script>

<style lang="scss">
  @import "../../../../assets/scss/common/list/card/cardList";
</style>
