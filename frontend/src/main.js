// Vue
import Vue from 'vue'
import App from './App'
// Router
import router from './router'
// Markdown
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
// Bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// Vue-i18n
import VueI18n from 'vue-i18n'
import languages from './assets/i18n/languages.vue'

Vue.config.productionTip = false
Vue.use(VueI18n)
Vue.use(ElementUI)
Vue.use(BootstrapVue)
Vue.use(mavonEditor)

// Vue-i18n Configuration
const i18n = new VueI18n({
  locale: 'ko', // set locale
  fallbackLocale: 'en',
  messages: languages // set locale messages
})

// change locale
// i18n.locale = 'ja'

// Nver Removed this
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  render: h => h(App)
})
