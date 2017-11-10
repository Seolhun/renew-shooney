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
          <input
            class="form-control"
            :placeholder="$tc('editor.placeholder.title')"
            v-model="blogContent.title"
          >
        </div>
      </div>

      <!-- Preview Markdown -->
      <div class="row margin-top-30">
        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.default.rawMarkdown') }}</p>
          <textarea
            id="editor"
            class="form-control auto-size"
            v-model="blogContent.content"
            @change.lazy="renderMarkdown"
          >
          </textarea>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.default.markdown') }}</p>
          <article
            class="form-control markdown-body"
            v-html="gitMarkdown"
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
  @import "../../../assets/scss/common/editor/git-markdown";
  @import "../../../assets/scss/common/editor/editor";
</style>

<script>
  import axios from 'axios'

  const blogAxios = axios.create({
    baseURL: 'http://localhost:5000',
    timeout: 10000,
    headers: {
      'Content-Type': 'application/json'
    }
  })

  export default {
    data () {
      return {
        currentDate: new Date().toLocaleTimeString(),
        blogContent: {
          title: '',
          contentType: 'Qna',
          content: '',
          createdByEntity: {
            nickname: 'SeolHun'
          }
        },
        gitMarkdown: '',
        results: [],
        types: [],
        errors: []
      }
    },
    methods: {
      insertContent () {
        blogAxios.post('/content', this.blogContent)
          .then(response => {
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      renderMarkdown () {
        axios.post('https://api.github.com/markdown', {
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
    watch: {},
    computed: {},
    created () {
      blogAxios.get('/api/v1/contentType')
        .then(response => {
          this.types = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })

      blogAxios.get('/content')
        .then(response => {
          this.results = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
</script>


