<template>
  <div class="container">
    <form>
      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
          <label class="title">
            {{ $tc('content.label.contentType') }}
          </label>

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
          <label class="title"> {{ $tc('content.label.tags') }}</label>
          <app-tag-input
            :on-change="convertTagEntity"
            :tags="inputTags"
            :placeholder="$t('content.placeholder.tags')"
            :class="'form-control'"
          >

          </app-tag-input>
        </div>
      </div>

      <div class="row margin-top-30">
        <div class="col-md-8 col-md-offset-4 col-sm-10 col-sm-offset-2">
          <label class="title"> {{ $tc('content.label.title') }}</label>
          <input
            class="form-control"
            :placeholder="$t('content.placeholder.title')"
            v-model="blogContent.title"
          >
        </div>
      </div>

      <!-- Preview Markdown -->
      <div
        class="row margin-top-30"
        id="writeFullScreen"
      >
        <div class="col-md-6 col-sm-6 col-xs-12">
          <label class="title">{{ $tc('editor.label.rawMarkdown') }}</label>
          <textarea
            id="rawMarkdown"
            class="form-control auto-size"
            v-model="blogContent.content"
            :placeholder="$t('editor.placeholder.rawMarkdown')"
            @change.lazy="renderMarkdown"
            @input="autoHeightStyleByEvent($event)"
          >
          </textarea>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-12">
          <label class="title">
            {{ $tc('editor.label.gitMarkdown') }}
          </label>
          <button
            v-show="isShowMarkdown == true"
            type="button"
            class="btn-sm btn-royalblue margin-10"
            @click="changeFullScreen('#gitMarkdown')"
          >
            {{ $t('common.fullScreen') }}
          </button>
          <div
            v-show="isShowMarkdown == true"
            id="gitMarkdown"
            class="form-control markdown-body"
            v-html="gitMarkdown"
            @change="autoHeightStyleByEvent($event)"
          >
          </div>
          <div v-show="isShowMarkdown == false">
            <label>{{ $tc('editor.placeholder.gitMarkdown') }}</label>
          </div>
        </div>
      </div>

      <div class="row margin-top-30 text-right">
        <div class="col-md-12 col-sm-12">
          <button
            type="button"
            class="btn btn-royalblue margin-10"
            @click="changeFullScreen('#writeFullScreen')"
          >
            {{ $tc('editor.label.writeFullScreen') }}
          </button>
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
        isShowMarkdown: false,
        gitMarkdown: '',
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
        }).then(response => {
          this.changeFullScreen('#writeFullScreen')
          this.gitMarkdown = response.data
          this.isShowMarkdown = this.blogContent.content.length > 0
        }).catch(e => {
          this.errors.push(e)
        })
      }
    },
    created () {
      this.$http.get('http://localhost:5000/api/v1/contentType')
        .then(response => {
          this.types = response.data
        }).catch(e => {
        this.errors.push(e)
      })

      this.$http.get('http://localhost:5000/content')
        .then(response => {
          this.results = response.data
        }).catch(e => {
        this.errors.push(e)
      })

      this.$http.get('http://localhost:5000/tag')
        .then(response => {
          this.tags = response.data
        }).catch(e => {
        this.errors.push(e)
      })
    }
  }
</script>
