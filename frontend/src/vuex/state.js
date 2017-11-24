import Vue from 'vue'
import Vuex from 'vuex'
import contentState from './modules/commonList'

Vue.use(Vuex)
export const store = new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    contentState
  }
})
