// import * as alias from '../alias'

const state = {
  searchedResults: {},
}

const getters = {
  getSearchedList: state => {
    return state.searchedResults
  }
}

const mutations = {
  /**
   * See {@link @/commponents/items/content/Content.vue} Results
   */
  setSearchedList: (state, payload) => {
    // Set State filtered result
    state.searchedResults = payload
    // results: {
    //   items: DummyContents,
    //     pageIndex: 1,
    //     pageSize: 12,
    //     totalCount: DummyBlogs.length
    // },
  }
}

const actions = {
  setSearchedList: ({commit}, payload) => {
    commit('setSearchedList', payload)
  },
  // asyncDecrement: ({commit}, payload) => {
  //   setTimeout(() => {
  //     commit('decrement', payload.by)
  //   }, payload.duration)
  // }

  // // getData() 및 getOtherData()가 Promise를 반환한다고 가정합니다.
  // async actionA ({commit}) {
  //   commit('gotData', await getData())
  // },
  // async actionB ({dispatch, commit}) {
  //   await dispatch('actionA') // actionA가 끝나기를 기다립니다.
  //   commit('gotOtherData', await getOtherData())
  // }
}

export default {
  state,
  getters,
  mutations,
  actions
}
