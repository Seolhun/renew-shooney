<template>
  <div class="container">
    <form>
      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
          <select
            class="form-control"
            v-model="blogContent.contentType"
          >
            <option
              v-for="type in types"
              :selected="type"
            >
              {{ type }}
            </option>
          </select>
        </div>
      </div>

      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
          <lable> {{ $tc('content.placeholder.tags') }}
            <app-tag-input
              :on-change="convertTagEntity"
              :tags="inputTags"
              :class="'form-control'"
            >

            </app-tag-input>
          </lable>
        </div>
      </div>

      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
            <input
              class="form-control"
              :placeholder="$t('content.placeholder.title')"
              v-model="blogContent.title"
            >
        </div>
      </div>

      <!-- Preview Markdown -->
      <div class="row margin-top-30">
        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.view.rawMarkdown') }}</p>
          <textarea
            id="editor"
            class="form-control auto-size"
            v-model="blogContent.content"
            @change.lazy="renderMarkdown"
          >
          </textarea>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.view.markdown') }}</p>
          <article
            class="form-control markdown-body"
            v-html="rawMarkdown"
          >
          </article>
        </div>
      </div>

      <div class="row margin-top-30 text-right">
        <div class="col-md-12 col-sm-12">
          <button
            type="button"
            class="btn btn-ocean"
            @click="insertContent"
          >
            {{ $t('common.submit') }}
          </button>

          <button
            type="button"
            class="btn btn-gray"
          >
            {{ $t('common.cancel') }}
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style lang="scss">
  @import "../../../assets/scss/common/editor/gitMarkdown";
</style>

<script>
  import TagInput from '@/components/common/editor/TagInput'

  export default {
    components: {
      'appTagInput': TagInput
    },
    data () {
      return {
        inputTags: [],
        blogContent: {
          title: '',
          tags: [],
          contentType: 'Qna',
          content: '',
          baseCreatedBy: {
            createdByNickname: 'SeolHun'
          }
        },
        rawMarkdown: '',
        results: [],
        types: [],
        errors: []
      }
    },
    methods: {
      convertTagEntity () {
        this.inputTags.forEach((value) => {
          this.blogContent.tags.push({name: value})
        })
      },

      insertContent () {
        this.$http.post('http://localhost:5000/content', this.blogContent)
          .then(response => {
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      renderMarkdown () {
        this.$http.post('https://api.github.com/markdown', {
          text: this.blogContent.content,
          mode: 'gfm',
          context: 'github/gollum'
        })
          .then(response => {
            this.gitMarkdown = response.data
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    created () {
      this.$http.get('http://localhost:5000/api/v1/contentType')
        .then(response => {
          this.types = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })

      this.$http.get('http://localhost:5000/content')
        .then(response => {
          this.results = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })

      this.$http.get('http://localhost:5000/tag')
        .then(response => {
          this.tags = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
</script>


