<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-sm-12">
          <h1>
            {{ blogContent.title }}
          </h1>
        </div>
      </div>
    </div>

    <div class="row">
      <div>
        <b-img
          src="https://cdn-images-1.medium.com/max/2000/1*gs2hvSZS09vGYwds-teNqw.jpeg"
          fluid
          alt="Responsive image"
        />
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-12 col-sm-12">
          <div
            class="shadow-box-1"
          >
            <span v-for="tag in blogContent.tags">
              <button class="label-title-16 margin-10 btn btn-edge-ocean">
                {{ tag.name }}
              </button>
            </span>
          </div>

          <div
            class="margin-top-30"
            v-html="blogContent.markdownContent"
          >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        link: {
          name: 'BlogDetail',
          params: {
            nickname: this.$route.params.nickname,
            idx: this.$route.params.idx
          },
          query: {
            locale: 'en',
            q: 100
          },
          hash: '#data'
        },
        blogContent: {
          title: '',
          tags: [],
          contentType: 'Qna',
          content: '',
          gitMarkdown: '',
          baseCreatedBy: {
            createdByNickname: 'SeolHun'
          }
        }
      }
    },
    methods: {},
    computed: {
      getContentById () {
        let idx = this.$route.params.idx
        let nickname = this.$route.params.nickname
        return {'idx': idx, 'nickname': nickname}
      }
    },
    created () {
      this.$http.get(`http://localhost:5000/content/${this.link.params.nickname}/${this.link.params.idx}`)
        .then(response => {
          this.blogContent = response.data
        }).catch(e => {
        this.errors.push(e)
      })
    }
  }
</script>

<style lang="scss">
  @import "../../../assets/scss/common/editor/gitMarkdown";
</style>
