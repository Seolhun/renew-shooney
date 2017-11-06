<template>
  <div class="container">
    <form>
      <div class="el-row">
        <div class="el-col-md-20 el-col-md-offset-4 el-col-sm-20 el-col-sm-offset-4">
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

      <div class="el-row">
        <div class="el-col-md-20 el-col-md-offset-4 el-col-sm-20 el-col-sm-offset-4">
          <input
            class="form-control"
            v-model="content.title"
          >

        </div>
      </div>

      <div class="el-row">
        <div class="el-col-md-20 el-col-md-offset-4 el-col-sm-20 el-col-sm-offset-4">
        <textarea
          v-model="content.content"
          cols="60"
        >

        </textarea>
        </div>
      </div>

      <div class="el-row">
        <div class="el-col-md-20 el-col-md-offset-4 el-col-sm-20 el-col-sm-offset-4">
          <button
            type="button"
            class="el-button el-button--info"
            @click="insertContent"
          >
            Submit
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
  import axios from 'axios'

  const instance = axios.create({
    baseURL: 'http://127.0.0.1:5000',
    timeout: 10000
//    headers: {'X-Custom-Header': 'shooney'}
  })

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
        results: [],
        types: [],
        errors: []
      }
    },
    methods: {
      insertContent () {
        instance.post('/content', {
          content: this.content
        })
          .then(response => {
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    watch: {},
    computed: {},
    created () {
      instance.get('/api/v1/contentType')
        .then(response => {
          this.types = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })

      instance.get('/content')
        .then(response => {
          this.results = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
</script>
