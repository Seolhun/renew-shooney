import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);
var store = new Vuex.Store({
    state: {
        count: 0
    },
    mutations: {
        increment: function (state) {
            state.count++;
        },
        decrement: function (state) {
            state.count--;
        }
    },
    actions: {}
});
export default store;
//# sourceMappingURL=vuex.js.map
