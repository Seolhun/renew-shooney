import Vue from 'vue'
import Vuex from 'vuex'
import content from './modules/content'
import user from './modules/user'
import tag from './modules/tag'


Vue.use(Vuex)
export const store = new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    user,
    content,
    tag
  }
})
