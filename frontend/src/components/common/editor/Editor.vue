<template>
  <div class="container">
    <form>
      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
          <select
            class="form-control"
            v-model="content.contentType"
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
            v-model="content.title"
          >
        </div>
      </div>

      <!-- Preview Markdown -->
      <div class="row margin-top-30">
        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.default.rawMarkdown') }}</p>
          <textarea
            id="editor"
            class="form-control min-height-100"
            v-model="content.content"
            @change.lazy="renderMarkdown"
          >

          </textarea>
        </div>


        <div class="col-md-6 col-sm-6 col-xs-12">
          <p class="font-weight-600">{{ $t('editor.default.markdown') }}</p>
          <article
            class="form-control min-height-100 markdown-body"
            v-html="gitMarkdown"
          >
          </article>
        </div>
      </div>

      <div class="row margin-top-30">
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
  @import "../../../assets/scss/editor/git-markdown";
  @import "../../../assets/scss/editor/editor";
</style>

<script>
  import axios from 'axios'

  //  const instance = axios.create({
  //    baseURL: 'http://127.0.0.1:5000',
  //    timeout: 10000
  //    headers: {'X-Custom-Header': 'shooney'}
  //  })

  export default {
    data () {
      return {
        currentDate: new Date().toLocaleTimeString(),
        content: {
          title: '',
          contentType: 'Essay',
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
        axios.post('/content', {
          content: this.content
        })
          .then(response => {
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      renderMarkdown () {
        axios.post('https://api.github.com/markdown', {
          text: this.content.content,
          mode: 'gfm',
          context: 'github/gollum'
        })
          .then(response => {
            console.log(response.data)
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
      axios.get('/api/v1/contentType')
        .then(response => {
          this.types = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })

      axios.get('/content')
        .then(response => {
          this.results = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
</script>


