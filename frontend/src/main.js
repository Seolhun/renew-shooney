// Vue
import Vue from 'vue'
import App from './App'
// Router
import router from './router'
// Element UI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
// Bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// Vue-i18n
import VueI18n from 'vue-i18n'
import messages from './assets/i18n/messages.vue'

Vue.config.productionTip = false
Vue.use(VueI18n)
Vue.use(ElementUI)
Vue.use(BootstrapVue)

// Vue-i18n Configuration
const i18n = new VueI18n({
  // set locale
  locale: 'ko',
  fallbackLocale: 'en',
  messages: messages,
  silentTranslationWarn: true
  // set locale messages
})

// change locale
// i18n.locale = 'ja'

// Never Removed this
/* eslint-disable no-new */
const vm = new Vue({
  el: '#app',
  router,
  i18n,
  render: h => h(App)
})

export default vm
