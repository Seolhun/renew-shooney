<template>
  <div
    @click="focusNewTag()"
    :class="{'read-only': readOnly}"
    class="vue-input-tag-div"
  >
    <span
      v-for="(tag, index) in tags"
      :key="index"
      class="input-tag"
    >
      <span>{{ tag }}</span>
      <a
        v-if="!readOnly"
        @click.prevent.stop="remove(index)"
        class="remove"
      >
      </a>
    </span>
    <input
      v-if="!readOnly"
      :placeholder="placeholder"
      v-model="newTag"
      @keydown.delete.stop="removeLastTag()"
      @keydown.enter.188.tab.prevent.stop="addNew(newTag)"
      class="new-tag"
    />
  </div>
</template>


<style lang="scss">
  @import "../../../assets/scss/common/editor/tagInput";
</style>


<script>
  /*eslint-disable*/
  const validators = {
    email: new RegExp(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/),
    url: new RegExp(/^(https?|ftp|rmtp|mms):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i),
    text: new RegExp(/^[a-zA-Z]+$/),
    digits: new RegExp(/^[\d() \.\:\-\+#]+$/),
    isodate: new RegExp(/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/)
  }
  /*eslint-enable*/
  export default {
    props: {
      tags: {
        type: Array
      },
      placeholder: {
        type: String
      },
      onChange: {
        type: Function
      },
      readOnly: {
        type: Boolean
      },
      validate: {
        type: String
      }
    },
    data () {
      return {
        newTag: ''
      }
    },
    methods: {
      focusNewTag () {
        if (this.readOnly) {
          return
        }
        this.$el.querySelector('.new-tag').focus()
      },
      addNew (tag) {
        if (tag && this.tags.indexOf(tag) === -1 && this.validateIfNeeded(tag)) {
          this.tags.push(tag)
          this.tagChange()
        }
        this.newTag = ''
      },
      validateIfNeeded (tagValue) {
        if (this.validate === '' || this.validate === undefined) {
          return true
        } else if (Object.keys(validators).indexOf(this.validate) > -1) {
          return validators[this.validate].test(tagValue)
        }
        return true
      },
      remove (index) {
        this.tags.splice(index, 1)
        this.tagChange()
      },
      removeLastTag () {
        if (this.newTag) {
          return
        }
        this.tags.pop()
        this.tagChange()
      },
      tagChange () {
        if (this.onChange) {
          // avoid passing the observer
          this.onChange(JSON.parse(JSON.stringify(this.tags)))
        }
      }
    }
  }
</script>
