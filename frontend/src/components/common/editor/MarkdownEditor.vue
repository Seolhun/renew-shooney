<template>
  <div class="container" id="markdown">
    <div class="el-row">
      <div class="el-col-md-24">
        <mavon-editor
          language="en"
          placeholder="Getting started Markdown"
          default_open="edit"
          :ishljs = "true"
          :model="content"

        >
        </mavon-editor>
      </div>
    </div>
  </div>
</template>

<script>
  import MavonEditor from 'mavon-editor'

  export default {
    name: 'markdown',
    components: {
      'MavonEditor': MavonEditor.mavonEditor
    },
    data () {
      return {
        currentDate: new Date().toLocaleTimeString(),
        anchorAttrs: {
          target: '_blank',
          rel: 'noopener noreferrer nofollow'
        },
        content: '## This Content'
      }
    },
    methods: {},
    watch: {
    },
    mounted () {
      setInterval(() => {
        this.currentDate = new Date().toLocaleTimeString()
      }, 1000)
    }
  }
</script>
