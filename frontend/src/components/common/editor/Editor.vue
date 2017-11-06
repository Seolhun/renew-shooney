<template>
  <div class="container">
    <form>
      <div class="el-row">
        <div class="el-col-md-8 el-col-md-offset-8 el-col-sm-16 el-col-sm-offset-8">
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
        <div class="el-col-md-8 el-col-md-offset-8 el-col-sm-16 el-col-sm-offset-8">
          <input
            class="form-control"
            v-model="content.title"
          >

        </div>
      </div>

      <div class="el-row">
        <div class="el-col-md-8 el-col-md-offset-8 el-col-sm-16 el-col-sm-offset-8">
        <textarea
          v-model="content.content"
          cols="60"
        >

        </textarea>
        </div>
      </div>

      <div class="el-row">
        <div class="el-col-md-8 el-col-md-offset-8 el-col-sm-16 el-col-sm-offset-8">
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
        types: [],
        errors: []
      }
    },
    methods: {
      insertContent () {
        axios.post('http://127.0.0.1/5000/content', {
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
      axios.get('http://127.0.0.1/5000/api/v1/contentTypes')
        .then(response => {
          this.types = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
</script>
