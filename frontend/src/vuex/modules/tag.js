// import * as alias from '../alias'

const state = {
  tags: [],
}

const getters = {
  getTags: state => {
    return state.tags
  }
}

const mutations = {
  /**
   * See {@link @/commponents/items/content/Content.vue} Results
   */
  setTags: (state, payload) => {
    state.tags = payload
  }
}

const actions = {
  setTags: ({commit}, payload) => {
    commit('setTags', payload)
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
