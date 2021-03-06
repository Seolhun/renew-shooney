// Vue
import Vue from 'vue';
import App from './App.vue';
// Router
import router from './router/index';
// Vuex
import store from './state/state';
// Bootstrap
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
// Vue-i18n
import VueI18n from 'vue-i18n';
import messages from './assets/i18n/messages.vue';
// Axios
import axios from 'axios';
Vue.config.productionTip = false;
Vue.use(VueI18n);
Vue.use(BootstrapVue);
// Vue-i18n Configuration
var i18n = new VueI18n({
    // set locale
    locale: 'ko',
    fallbackLocale: 'en',
    messages: messages,
    silentTranslationWarn: true
    // set locale messages
});
// change locale
// i18n.locale = 'ja'
// Never Removed this
/* eslint-disable no-new */
Vue.prototype.$appName = 'Hi-Cord';
Vue.prototype.$http = axios;
var vm = new Vue({
    el: '#app',
    router: router,
    store: store,
    i18n: i18n,
    render: function (h) { return h(App); }
});
export default vm;
//# sourceMappingURL=route.js.map
